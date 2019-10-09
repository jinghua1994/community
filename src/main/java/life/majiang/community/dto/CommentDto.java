package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private   long id;
    private  String  comment;
    private  Long parentId;
    private  long   gmtModified;
    private  long gmtCreate;
    private  Integer type;
    private  int commentator;
    private   long likeCount;
    private User user;

}
