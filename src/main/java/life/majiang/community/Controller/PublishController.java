package life.majiang.community.Controller;

import life.majiang.community.Service.QuestionService;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")Integer id,Model model){
        Question question=questionMapper.findQuestionById(id);
        model.addAttribute("tag",question.getTag());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("title",question.getTitle());
        model.addAttribute("id",id);
        return  "publish";
    }

    @GetMapping("/publish")
    public  String  publish(){

        return "publish";
    }
    @PostMapping("/publish")
    public  String  doPublish(@RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "description",required = false) String description,
                              @RequestParam(value="tag",required = false) String tag,
                              @RequestParam(value="id",required = false) Integer id,
                              HttpServletRequest request,
                              Model model){
        model.addAttribute("description",description);
        model.addAttribute("title",title);
        model.addAttribute("tag",tag);
        if(title==null|| title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null|| description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }  if(tag==null|| tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    String token=cookie.getValue();
                     user= userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }


        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
         questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
