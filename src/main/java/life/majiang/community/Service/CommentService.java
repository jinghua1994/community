package life.majiang.community.Service;

import life.majiang.community.Enums.CommentTypeEnum;
import life.majiang.community.Exception.CustomizeErrorCode;
import life.majiang.community.Exception.CustomizeException;
import life.majiang.community.dto.CommentDto;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Transactional
    public  void insert(Comment comment){
        if(comment.getParentId()==null ||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.Comment.getType()){
            //回复评论
           Comment dbcomment=commentMapper.findCommentById(comment.getParentId());
            if(dbcomment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.incView(comment);
        }else{
            //回复问题
            Question question=questionMapper.findQuestionById(comment.getParentId().intValue());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.incView(comment);
            questionMapper.addComment(comment.getParentId());
        }
    }


    public List<CommentDto> listByQuestionId(Long id){
       List<Comment> comments=commentMapper.findCommentByParentAndType(id);
       if(comments.size()==0){
           return new ArrayList<CommentDto>();
       }
        Map<Long, User>  userMap=new HashMap<>();
       for(Comment comment:comments){
          User user=userMapper. findById(comment.getCommentator());
          userMap.put(comment.getCommentator(),user);
       }
       List<CommentDto> commentDtos=new LinkedList<>();
        for(Comment comment:comments){
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            commentDtos.add(commentDto);
        }

            return commentDtos;
    }
}
