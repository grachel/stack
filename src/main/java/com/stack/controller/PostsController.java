package com.stack.controller;

import com.stack.model.dao.Post;
import com.stack.model.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/post")
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

        Post post = new Post(User.getCurrentUser());
        post.setTitle(title);
        post.setBody(body);
        post.setType("Q");
        post.save();

        return new ModelAndView("redirect:/me");
    }

    @RequestMapping(value = "my", method = RequestMethod.GET)
    public ModelAndView me() {
        User currentUser = User.getCurrentUser();

        ModelAndView model = new ModelAndView();
        model.addObject("posts", currentUser.getPosts());
        model.setViewName("post/me");

        currentUser.close();
        return model;

    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        User currentUser = User.getCurrentUser();

        ModelAndView model = new ModelAndView();
        Post post = new Post(id);
        
        model.addObject("post", post);
        model.addObject("currUser", currentUser);
        model.setViewName("post/view");

        currentUser.close();
        post.close();
        return model;

    }
}
