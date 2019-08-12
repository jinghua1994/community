package life.majiang.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state)
    {
        return  "index";
    }
}
