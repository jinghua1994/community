package life.majiang.community.Controller;


import life.majiang.community.Exception.CustomizeErrorCode;
import life.majiang.community.Exception.CustomizeException;
import life.majiang.community.Service.CommentService;
import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.CommentReceiveDto;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public  Object post(@RequestBody CommentReceiveDto commentReceiveDto, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
       if(user==null){
           return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
       }
        if(commentReceiveDto==null|| StringUtils.isBlank(commentReceiveDto.getComment())){
            return ResultDto.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment=new Comment();
        comment.setComment(commentReceiveDto.getComment());
        comment.setParentId(commentReceiveDto.getParentId());
        comment.setCommentator(user.getId().longValue());
        comment.setComment(commentReceiveDto.getComment());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(comment.getGmtModified());
        comment.setType(commentReceiveDto.getType());
        commentService.insert(comment);
        return ResultDto.okOf();
    }
}
