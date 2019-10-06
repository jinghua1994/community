package life.majiang.community.model;

import lombok.Data;

@Data
public class Comment {
    private   long id;
    private  String  comment;
    private  long parentId;
    private  long   gmtModified;
    private  long gmtCreate;
    private  int type;
    private  int commentator;
    private   long likeCount;
}
