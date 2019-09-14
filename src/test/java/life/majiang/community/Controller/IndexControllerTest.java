package life.majiang.community.Controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.Assert.*;

public class IndexControllerTest {
    @Autowired
    private QuestionMapper questionMapper;
    @Test
    public void index() {

        List<Question> questionList= questionMapper.list();
        System.out.println(questionList);
    }
}