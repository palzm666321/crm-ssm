package com.bjpowernode.controllers;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.UUIDUtil;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clue")
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClueService clueService;

    @RequestMapping("/index.do")
    public ModelAndView doIndex(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("userAll",userService.getAll());
        mv.setViewName("clue/index");
        return mv;
    }

    public void doInsert(Clue clue){
        clue.setId(UUIDUtil.getUUID());
        System.out.println("爱上大傻傻的");
        clueService.addClue(clue);
    }


}
