package shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UserController {

    @RequestMapping("/login")
    public String login() {
        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken("brian", "123456");
        subject.login(token);
        return "index";
    }

    @RequestMapping("/readName")
    public String readName() {
        return "name";
    }

    @RequestMapping("/readData")
    public String readData(){
        return "data";
    }

    @RequestMapping("/noPermission")
    public String noPermission(){
        return "unauthor";
    }
}
