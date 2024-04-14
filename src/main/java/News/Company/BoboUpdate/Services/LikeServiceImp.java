//package News.Company.BoboUpdate.Services;
//
//
//
//import News.Company.BoboUpdate.Data.Model.Like;
//import News.Company.BoboUpdate.Data.Repositories.LikeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class LikeServiceImp implements LikeService {
//    @Autowired
//    LikeRepository likeRepository;
//
//    @Override
//    public Like addLikePost(String userId, String postId) {
//        Like like = likeRepository.findByPostId(postId);
//        if (like == null) {
//            like = new Like();
//            like.setPostId(postId);
//        }
//        like.getLikes().add(userId);
//
//        return likeRepository.save(like);
//    }
//    }
//
//
