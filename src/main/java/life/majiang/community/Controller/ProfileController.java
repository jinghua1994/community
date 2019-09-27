package life.majiang.community.Controller;

import com.github.pagehelper.PageHelper;
import life.majiang.community.Service.CookieService;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    CookieService cookieService;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model, HttpServletRequest request,
                          @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        Cookie[] cookies=request.getCookies();
        cookieService.AddUser(cookies,request);
       if("question".equals(action)){
           model.addAttribute("section","question");
           model.addAttribute("sectionName","我的提问");
       }else if("replies".equals(action)){
           model.addAttribute("section","replies");
           model.addAttribute("sectionName","最新回复");
       }
        User user=(User) request.getSession().getAttribute("user");
//       Integer temp_id= userMapper.findIdByToken(user.getToken());
        PageHelper.startPage(pn,5);
        questionMapper.findQuestionByCreator(user.getId());
        return "profile";

    }




}
