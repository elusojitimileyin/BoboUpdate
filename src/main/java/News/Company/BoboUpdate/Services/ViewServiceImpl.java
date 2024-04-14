package News.Company.BoboUpdate.Services;


import News.Company.BoboUpdate.Data.Model.View;
import News.Company.BoboUpdate.Data.Repositories.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ViewServiceImpl implements ViewService {


    @Autowired
    private ViewRepository viewRepository;


    @Override
    public View  addView(String userId, String postId) {
        View view = viewRepository.findByPostId(postId);
        if (view == null) {
            view = new View();
            view.setPostId(postId);
        }
        view.getViewer().add(userId);

        return viewRepository.save(view);
    }
}
