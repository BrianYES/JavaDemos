package brian.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import brian.pojo.CodeMsg;
import brian.pojo.Result;
import brian.pojo.User;
import brian.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success() {
        return Result.success("hello spring boot");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result error() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Brian");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result getUser() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result insert() {
        userService.insert();
        return Result.success("ok");
    }

}
