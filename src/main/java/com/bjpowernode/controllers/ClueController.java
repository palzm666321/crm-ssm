package com.bjpowernode.controllers;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clue")
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClueService clueService;

    @RequestMapping("/index.do")
    public String doIndex(Model model,
                                Clue clue,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo<Clue> moviePageInfo=clueService.splitPage(clue,pageNum,pageSize);
        model.addAttribute("clueSplitAll",moviePageInfo);
        model.addAttribute("userAll",userService.getAll());
        return "clue/index";
    }




}
