package life.majiang.community.Controller;

import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String githubId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String  redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request, HttpServletResponse response)
    {
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id(githubId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser=githubProvider.getUser(accessToken);
       if(githubUser!=null){
           String accoundId=githubUser.getId();
           User temp_user=userMapper.findByAccountId(accoundId);
           if(temp_user==null){
               User user=new User();
               String token = UUID.randomUUID().toString();
               user.setToken(token);
               user.setName(githubUser.getName());
               user.setAccountId(String.valueOf(githubUser.getId()));
               user.setGmtCreate(System.currentTimeMillis());
               user.setGmtModified(user.getGmtCreate());
               user.setAvatar_url(githubUser.getAvatar_url());
               userMapper.insert(user);
               request.getSession().setAttribute("user",user);
               response.addCookie(new Cookie("token",user.getToken()));
           }else if(temp_user!=null){
               request.getSession().setAttribute("user",temp_user);
               response.addCookie(new Cookie("token",temp_user.getToken()));
           }

                return "redirect:/";
       }else{
                return "redirect:/";
       }

    }
}
