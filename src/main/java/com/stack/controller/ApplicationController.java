package com.stack.controller;

import com.stack.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ApplicationService applicationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping("/config")
    public @ResponseBody Map<String,String> config() {
        Map<String, String> params = new HashMap<>();

        params.put("projectName", applicationService.getProjectName());
        params.put("projectAuthor", applicationService.getProjectAuthor());
        params.put("projectWebsite", applicationService.getProjectWebsite());

        logger.info("Querying /config");

        return params;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {

        ModelAndView model = new ModelAndView();
        model.setViewName("home");

        return model;
    }
}
