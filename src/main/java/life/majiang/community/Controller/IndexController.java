package life.majiang.community.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Msg;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        // pagehelper分页插件，在使用之前需要调用，传入起始的页码和每页的大小
        PageHelper.startPage(pn,5);
        List<Question> questionList= questionMapper.list();
        //使用pageinfo来包装查询的结果，只需要将pageinfo的结果交给页面就可以了，封装了详细的页面信息，包括查询出来的数据
        PageInfo<Question>  page=new PageInfo<Question>(questionList,5);
        // model.addAttribute("pageinfo",page);
        return Msg.success().add("pageinfo",page);
    }

}
