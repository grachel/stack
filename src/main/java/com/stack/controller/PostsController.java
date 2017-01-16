package com.stack.controller;

import com.stack.model.dao.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostsController {

    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public ModelAndView ask() {

        ModelAndView model = new ModelAndView();
        model.setViewName("post/ask");

        return model;

    }

    @RequestMapping(value = "ask", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "body") String body) {

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setType("Q");
        post.save();

        return new ModelAndView("redirect:/");
    }
}
