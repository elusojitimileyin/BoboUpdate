package News.Company.BoboUpdate.Controller;


import News.Company.BoboUpdate.BoboUpdateException.BoboUpdateException;
import News.Company.BoboUpdate.Dtos.request.CreateCommentRequest;
import News.Company.BoboUpdate.Dtos.response.ApiResponse;
import News.Company.BoboUpdate.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CreateCommentRequest createCommentRequest) {
        try {
            var result = commentService.createComment(createCommentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
}