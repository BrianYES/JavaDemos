package com.brian.web;

import com.alibaba.fastjson.JSONObject;
import com.brian.bo.CategoryBo;
import com.brian.pojo.Category;
import com.brian.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryBo bo;

    @RequestMapping("listCategory")
    public ModelAndView listAllCategory(Page page) {
//        List<Category> categoryList = this.bo.getAll();
//        List<Category> categoryList = this.bo.list(page);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> categoryList = this.bo.list();

//        int totalCount = this.bo.totalCount();
        int totalCount = (int) new PageInfo<>(categoryList).getTotal();

        page.caculateLast(totalCount);

        ModelAndView mav = new ModelAndView("listCategory");
        mav.addObject("categoryList", categoryList);
        return mav;
    }


    @RequestMapping(value = "getOneCategory", produces = "application/json; charset=utf-8")
    public @ResponseBody String getOneCategory() {
        Category c = new Category();
        c.setId(100);
        c.setName("第100个分类");
        JSONObject json= new JSONObject();
        json.put("category", JSONObject.toJSON(c));
        return json.toJSONString();
    }

    @RequestMapping("addTwo")
    public @ResponseBody String addTwoCategory() {
        this.bo.addTwo();

        return "ok";
    }

}
