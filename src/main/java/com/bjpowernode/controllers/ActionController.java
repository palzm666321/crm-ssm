package com.bjpowernode.controllers;

import com.bjpowernode.domain.User;
import com.bjpowernode.exceptions.LoginException;
import com.bjpowernode.exceptions.MyException;
import com.bjpowernode.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String doLogin(String name, String password, HttpServletRequest request)throws LoginException {
        ModelAndView mv=new ModelAndView();
        String ip=request.getRemoteAddr();
        User user = actionService.login(name, password, ip);
        request.getSession().setAttribute("user",user);
    //    mv.addObject("user",user);
    //    mv.setViewName("index");
        return "index";
    }

    @RequestMapping(value = "/index.do")
    public ModelAndView doIndex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("main/index");
        return mv;
    }



}
