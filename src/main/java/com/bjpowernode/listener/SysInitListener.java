package com.bjpowernode.listener;

import com.bjpowernode.domain.DicValue;
import com.bjpowernode.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext=sce.getServletContext();

        DicValueService dicValueService=WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(DicValueService.class);

        Map<String, List<DicValue>> map=dicValueService.getAll();

        Set<String> set=map.keySet();

        set.forEach(i -> servletContext.setAttribute(i,map.get(i)));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
