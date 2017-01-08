package com.stack.controller;

import com.stack.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public ApplicationController() { }

    @RequestMapping("/config")
    public @ResponseBody Map<String,String> config() {
        Map<String, String> params = new HashMap<String, String>();

        params.put("projectName", applicationService.getProjectName());
        params.put("projectAuthor", applicationService.getProjectAuthor());
        params.put("projectWebsite", applicationService.getProjectWebsite());

        logger.info("Querying /config");

        return params;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {

        ModelAndView model = new ModelAndView();
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            model.setViewName("home");
        }
        else {
            model.setViewName("login");
        }

        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {

        ModelAndView model = new ModelAndView();
        model.setViewName("main");

        return model;
    }

}
