package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDto  {
    private Integer id;
    private String title;
    private  String description;
    private long  gmtCreate;
    private long  gmtModified;
    private int creator;
    private int commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String  tag;
    private User user;
}
