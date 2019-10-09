package life.majiang.community.model;

import lombok.Data;

@Data
public class Comment {
    private   long id;
    private  String  comment;
    private  Long parentId;
    private  long   gmtModified;
    private  long gmtCreate;
    private  Integer type;
    private  Long commentator;
    private   long likeCount;
}
