package com.angcyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by angcyo on 2016-02-28 22:41.
 */
@Controller
public class FirstController {

    @Resource
    HttpServletRequest request;

    @RequestMapping("login")
    public ModelAndView doFirst() {
        request.setAttribute("msg", "来自 FirstController");
        return new ModelAndView("WEB-INF/login/login.jsp");
    }
}
