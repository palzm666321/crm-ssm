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

@Controller
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public ModelAndView doLogin(String name, String password,HttpServletRequest request)throws LoginException {
        ModelAndView mv=new ModelAndView();
        String ip=request.getRemoteAddr();
        User user = actionService.login(name, password, ip);
        mv.addObject("user",user);
       // mv.setViewName("forward:/WEB-INF/workbench/index.jsp");
        mv.setViewName("index");
        return mv;
    }



}
