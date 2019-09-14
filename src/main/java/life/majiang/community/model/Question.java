package life.majiang.community.model;

import lombok.Data;

@Data
public class Question {
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


}
