package life.majiang.community.Controller;

import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
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
   @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id",required = false)Integer id, Model model){
   QuestionDto question=questionMapper.findQuestionDtoById(id);
        model.addAttribute("question",question);
        return  "question";
    }


}
