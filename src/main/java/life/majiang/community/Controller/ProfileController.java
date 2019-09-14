package life.majiang.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model){
       if("question".equals(action)){
           model.addAttribute("section","question");
           model.addAttribute("sectionName","我的提问");
       }

        return "/profile";

    }




}
