package com.stack.controller;

import com.stack.model.dto.ScoreDTO;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

@Controller
public class PostsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "t", required=false) String t) {
        ModelAndView model = new ModelAndView();

        Iterable<Post> posts = new ArrayList<>();
        if(t != null){
            Tag x = databaseService.findTag(t);
            if(x != null) {
                posts = x.getPosts();
            }
        } else {
            posts = databaseService.findAllPosts();
        }
        model.addObject("posts", posts);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value = "/post/ask", method = RequestMethod.GET)
    public ModelAndView ask() {
        ModelAndView model = new ModelAndView();
        model.setViewName("post/ask");
        return model;
    }

    @RequestMapping(value = "/post/ask", method = RequestMethod.POST)
    public ModelAndView ask(@RequestParam(value = "title") String title,
                            @RequestParam(value = "body") String body,
                            @RequestParam(value = "tags") String tags) {

        Post post = new Post();
        post.setUser(databaseService.getCurrentUser());
        post.setTitle(title);
        post.setBody(body);
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        post.setTags(new HashSet<>());

        String[] splitted = tags.split(" ");

        for(String tag : splitted){
            Tag t = databaseService.findTag(tag);
            if(t == null){
                t = new Tag();
                t.setTag(tag);

                databaseService.save(t);
            }

            post.getTags().add(t);
        }

        databaseService.save(post);

        return new ModelAndView("redirect:/post/my");
    }

    @RequestMapping(value = "post/my", method = RequestMethod.GET)
    public ModelAndView me() {
        ModelAndView model = new ModelAndView();
        model.addObject("posts", databaseService.getCurrentUser().getPosts());
        model.setViewName("post/me");
        return model;

    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        Post post = databaseService.findPost(Integer.parseInt(id));
        ModelAndView model = new ModelAndView();
        model.addObject("post", post);
        model.addObject("currUser", databaseService.getCurrentUser());
        model.setViewName("post/view");
        return model;
    }

    @RequestMapping(value = "/post/vote", method = RequestMethod.POST)
    public @ResponseBody
    ScoreDTO vote(@RequestParam(value = "id") String id) {
        User user = databaseService.getCurrentUser();
        Post post = databaseService.findPost(Integer.parseInt(id));

        Vote vote = databaseService.findVoteByUserAndPost(user, post);
        if(vote == null && !user.equals(post.getUser())){
            vote = new Vote();
            vote.setUser(user);
            vote.setPost(post);

            post.setScore(post.getScore() + 1);

            databaseService.save(vote);
            databaseService.save(post);
        }

        return new ScoreDTO(post.getScore());
    }
}
