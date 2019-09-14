package life.majiang.community.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
   @Autowired
   private UserMapper userMapper;
   @Autowired
   private QuestionMapper questionMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    String token=cookie.getValue();
                    User user= userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        List<Question> questionList= questionMapper.list();
//       PageHelper.startPage(pn,5);
//        PageInfo pageInfo=new PageInfo(questionList,5);

        model.addAttribute("questions",questionList);

        return "index";
    }

}
