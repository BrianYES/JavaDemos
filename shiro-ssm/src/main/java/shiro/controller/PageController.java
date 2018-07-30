package shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequiresPermissions("删除当前Tap")
    @RequestMapping("deleteOrder")
    public String deleteOrder(){
        return "deleteOrder";
    }

    @RequiresRoles("系统管理员")
    @RequestMapping("deleteProduct")
    public String deleteProduct(){
        return "deleteProduct";
    }

    @RequestMapping("listProduct")
    public String listProduct(){
        return "listProduct";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("unauthorized")
    public String noPerms(){
        return "unauthorized";
    }
}
