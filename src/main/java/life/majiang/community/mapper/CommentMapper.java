package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void  incView(Comment comment);

    Comment findCommentById(Long parentId);


    List<Comment> findCommentByParentAndType(Long id);
}
