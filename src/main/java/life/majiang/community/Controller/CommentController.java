package life.majiang.community.Controller;


import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public  Object post(@RequestBody CommentDto commentDto, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
       if(user==null){
           return ResultDto.errorOf("未登录",2002);
       }
        Comment comment=new Comment();
        comment.setComment(commentDto.getComment());
        comment.setParentId(commentDto.getParentId());
        comment.setCommentator(1);
        comment.setComment(commentDto.getComment());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(comment.getGmtModified());
        comment.setType(commentDto.getType());
        commentMapper.incView(comment);
        return null;
    }
}
