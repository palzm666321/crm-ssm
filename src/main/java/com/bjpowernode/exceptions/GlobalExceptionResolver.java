package com.bjpowernode.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(value = UserNullException.class)
    public ModelAndView doUserNullException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }
    @ExceptionHandler(value = UserIpException.class)
    public ModelAndView doUserIpException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }
    @ExceptionHandler(value = LockStateException.class)
    public ModelAndView doLockStateException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }
    @ExceptionHandler(value = ExpireTimeException.class)
    public ModelAndView doExpireTimeException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }
    @ExceptionHandler(value = LoginException.class)
    public ModelAndView doLoginException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }

    //处理其他异常
    @ExceptionHandler
    public ModelAndView doException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex);
        mv.setViewName("forward:/login.jsp");
        return mv;
    }


}
