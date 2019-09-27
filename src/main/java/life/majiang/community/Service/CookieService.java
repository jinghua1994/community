package life.majiang.community.Service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieService {
   @Autowired
    UserMapper userMapper;
        public void AddUser(Cookie[] cookies, HttpServletRequest request){
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
        }
}
