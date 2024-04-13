package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewPostResponse {
    private String post;
    private LocalDateTime  timeOfView;
}