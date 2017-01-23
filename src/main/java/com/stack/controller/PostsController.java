package com.stack.controller;

import com.stack.json.Answer;
import com.stack.json.Score;
import com.stack.model.dao.Post;
import com.stack.model.dao.User;
import com.stack.model.dao.Vote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

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
    public ModelAndView ask(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "body") String body) {

        Post post = new Post(User.getCurrentUser());
        post.setTitle(title);
        post.setBody(body);
        post.setType("Q");
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        post.save();

        return new ModelAndView("redirect:/post/my");
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

    @RequestMapping(value = "score", method = RequestMethod.POST)
    public @ResponseBody
    Score score(@RequestParam(value = "id") String id) {
        User currUser =  User.getCurrentUser();

        Post post = new Post(currUser.session);
        post.findById(id);

        if(post.getOwner().getId() != currUser.getId()){
            if(!post.wasVotedByUser(currUser)){
                post.incScore();
                Vote vote = new Vote(currUser.session);
                vote.setUser(currUser);
                vote.setPost(post);
                vote.persist();
            }
        }

        post.save();
        return new Score(post.getId(), post.getScore());
    }

    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public @ResponseBody
    Answer answer(@RequestParam(value = "postid") String postid,
                  @RequestParam(value = "body") String body) {

        Post post = new Post(User.getCurrentUser());
        post.setTitle("");
        post.setBody(body);
        post.setType("A");
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        post.setParent(postid);

        post.save();
        return new Answer(post.getId(), post.getBody(), post.getOwner().getDisplayName(), post.getCreationDate());
    }
}
