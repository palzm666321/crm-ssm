package com.bjpowernode.controllers;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.Tran;
import com.bjpowernode.service.ClueActivityRelationService;
import com.bjpowernode.service.ClueRemarkService;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/clue")
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

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

    @RequestMapping("/detail.do")
    public ModelAndView doDetail(String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("clue",clueService.getClueById(id));
        mv.addObject("clueRemarkList",clueRemarkService.getClueRemarkByIdAll(id));
        mv.setViewName("clue/detail");
        return mv;
    }

    @RequestMapping("/remark.do")
    public String doRemark(){
        return "clue/detail";
    }

    @RequestMapping("/convert.do")
    public ModelAndView doConvert(String id,ModelAndView mv){
        mv.addObject("clue",clueService.getClueById(id));
        mv.setViewName("clue/convert");
        return mv;
    }

    @RequestMapping("/insertContact.do")
    public String doInsertContact(Tran tran,String clueId,String flag,HttpServletResponse response){
        if ("a".equals(flag)){
            tran.setId(UUIDUtil.getUUID());
            tran.setCreateTime(DateTimeUtil.getSysTime());
        }else{
            tran=null;
        }
        clueService.addConvert(tran,clueId);
        return "logins";
    }

}
