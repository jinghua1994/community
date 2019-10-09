package life.majiang.community.Controller;

import life.majiang.community.Exception.CustomizeErrorCode;
import life.majiang.community.Exception.CustomizeException;
import life.majiang.community.Service.CommentService;
import life.majiang.community.Service.QuestionService;
import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
   @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id",required = false)Integer id, Model model){
       questionService.addview(id);
       QuestionDto question=questionMapper.findQuestionDtoById(id);
       if(question==null){
           throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
       }
       List<CommentDto> commentDtos = commentService.listByQuestionId(id.longValue());
        model.addAttribute("question",question);
       model.addAttribute("comments",commentDtos);
        return  "question";
    }


}
