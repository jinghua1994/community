package life.majiang.community.dto;


import lombok.Data;

@Data
public class CommentDto {

    private  long parentId;
    private  String  comment;
    private  Integer type;


}
