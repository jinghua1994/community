package life.majiang.community.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;
    @RequestMapping("/questionList")
    @ResponseBody
    public  PageInfo questionList(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,5);
        List<Question> questionList= questionMapper.list();
        PageInfo pageInfo=new PageInfo(questionList,5);
        return pageInfo;
    }
}
