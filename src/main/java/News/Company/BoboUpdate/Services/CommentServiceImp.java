package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Utils.BoboUpdateException;
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

import java.util.Optional;

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






    @Override
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) {
        User user = findUserByUsername(createCommentRequest.getUsername());
        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new BoboUpdateException("Post not found"));
        Comment newComment = new Comment();
        newComment.setPostId(post.getPostId());
        newComment.setComment(createCommentRequest.getContent());
        newComment.setPostId(post.getPostId());
        commentRepository.save(newComment);
        post.getComments().add(newComment);
        postRepository.save(post);
        if (!user.getId().equals(post.getPostId())) {
            viewService.addView(user.getId(), post.getPostId());
        }
        CreateCommentResponse response = new CreateCommentResponse();
        response.setPostId(post.getPostId());
        response.setContent(newComment.getComment());
        response.setUsername(user.getUsername());
        return response;
    }

    @Override
    public EditCommentResponse editComment(EditCommentRequest editCommentRequest) {

        Post post = postRepository.findById(editCommentRequest.getPostId())
                .orElseThrow(() -> new BoboUpdateException("Post not found"));
        Comment comment = commentRepository.findByPostId(post.getPostId())
                .orElseThrow(() -> new BoboUpdateException("Comment not found for postId: "));
        User user = userRepository.findByUsername(editCommentRequest.getUsername());
        if (!user.getId().equals(comment.getPostId())) {
            throw new BoboUpdateException("User does not have permission to edit this comment");
        }
        comment.setComment(editCommentRequest.getContent());
        commentRepository.save(comment);
        EditCommentResponse response = new EditCommentResponse();
        response.setPostId(post.getPostId());
        response.setContent(comment.getComment());
        response.setUsername(comment.getUsername());
        return response;
    }

    @Override
    public DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest) {
        User user = findUserByUsername(deleteCommentRequest.getUsername());
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId())
                .orElseThrow(() -> new BoboUpdateException("Comment not found"));
        if (!user.getId().equals(comment.getPostId())) {
            throw new BoboUpdateException("You are not authorized to delete this post");
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
            throw new BoboUpdateException("User with username " + username + " not found");
        }
        return user;
    }
    @Override
    public Optional<Comment> getComment(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BoboUpdateException("Post not found"));
        return commentRepository.findByPostId(post.getPostId());
    }

    @Override
    public Optional<Comment> getCommentsByUser(String postId, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BoboUpdateException("User not found"));
        return commentRepository.findByPostId(user.getId());
    }
}
