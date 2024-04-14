package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.BoboUpdateException.BoboUpdateException;
import News.Company.BoboUpdate.BoboUpdateException.PostNotFoundException;
import News.Company.BoboUpdate.BoboUpdateException.UserNotFoundException;
import News.Company.BoboUpdate.Data.Model.Comment;
import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.CommentRepository;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.CreateCommentRequest;
import News.Company.BoboUpdate.Dtos.request.DeleteCommentRequest;
import News.Company.BoboUpdate.Dtos.request.EditCommentRequest;
import News.Company.BoboUpdate.Dtos.response.CreateCommentResponse;
import News.Company.BoboUpdate.Dtos.response.DeleteCommentResponse;
import News.Company.BoboUpdate.Dtos.response.EditCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ViewService viewService;
//    @Autowired
//    private LikeService likeService;


    @Override
    public List<Comment> getComment(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        return commentRepository.findByPostId(post.getId());
    }

    @Override
    public List<Comment> getCommentsByUser(String postId, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return commentRepository.findByUserId(user.getId());
    }


    @Override
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) {
        User user = findUserByUsername(createCommentRequest.getUsername());

        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));


        Comment newComment = new Comment();
        newComment.setUserId(user.getId());
        newComment.setComment(createCommentRequest.getContent());
        newComment.setPostId(post.getId());
        commentRepository.save(newComment);
        post.getComments().add(newComment);
        postRepository.save(post);
        if (!user.getId().equals(post.getUserId())) {
            viewService.addView(user.getId(), post.getId());
        }

//        if (!user.getId().equals(post.getUserId())) {
//            likeService.addLikePost(user.getId(), post.getId());
//        }

        CreateCommentResponse response = new CreateCommentResponse();
        response.setPostId(post.getId());
        response.setContent(newComment.getComment());
        response.setUsername(user.getUsername());
        return response;
    }


    @Override
    public EditCommentResponse editComment(EditCommentRequest createCommentRequest) {

        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));


        Comment comment = commentRepository.findByPostIdAndUserId(post.getId(), post.getUserId())
                .orElseThrow(() -> new BoboUpdateException("Comment not found for postId: "));

        User user = userRepository.findByUsername(createCommentRequest.getUsername());
        if (!user.getId().equals(comment.getUserId())) {
            throw new UserNotFoundException("User does not have permission to edit this comment");
        }

        comment.setComment(createCommentRequest.getContent());

        commentRepository.save(comment);

        EditCommentResponse response = new EditCommentResponse();
        response.setPostId(post.getId());
        response.setContent(comment.getComment());
        response.setUsername(comment.getUserId());

        return response;
    }


    @Override
    public DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest) {
        User user = findUserByUsername(deleteCommentRequest.getUsername());
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId())
                .orElseThrow(() -> new BoboUpdateException("Comment not found"));

        if (!user.getId().equals(comment.getUserId())) {
            throw new UserNotFoundException("You are not authorized to delete this post");
        }
        Post post = postRepository.findById(comment.getPostId()).get();
        post.getComments().remove(comment);
        postRepository.save(post);
        commentRepository.delete(comment);
        return new DeleteCommentResponse();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
        return user;
    }

}
