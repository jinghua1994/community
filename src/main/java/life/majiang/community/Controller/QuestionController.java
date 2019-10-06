package life.majiang.community.Controller;

import life.majiang.community.Exception.CustomizeErrorCode;
import life.majiang.community.Exception.CustomizeException;
import life.majiang.community.Service.QuestionService;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
   @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id",required = false)Integer id, Model model){
       questionService.addview(id);
       QuestionDto question=questionMapper.findQuestionDtoById(id);
       if(question==null){
           throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
       }
        model.addAttribute("question",question);
        return  "question";
    }


}
