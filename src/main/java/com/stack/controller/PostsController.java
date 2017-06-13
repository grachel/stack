package com.stack.controller;

import com.stack.model.dto.ScoreDTO;
import com.stack.model.entities.Post;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.PostRepository;
import com.stack.model.repo.VoteRepository;
import com.stack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class PostsController {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    VoteRepository voteRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.addObject("posts", postRepository.findAll());
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
                            @RequestParam(value = "body") String body) {

        Post post = new Post();
        post.setUser(userService.getCurrentUser());
        post.setTitle(title);
        post.setBody(body);
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));

        postRepository.save(post);

        return new ModelAndView("redirect:/post/my");
    }

    @RequestMapping(value = "post/my", method = RequestMethod.GET)
    public ModelAndView me() {
        ModelAndView model = new ModelAndView();
        model.addObject("posts", userService.getCurrentUser().getPosts());
        model.setViewName("post/me");
        return model;

    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        Post post = postRepository.findOne(Integer.parseInt(id));
        ModelAndView model = new ModelAndView();
        model.addObject("post", post);
        model.addObject("currUser", userService.getCurrentUser());
        model.setViewName("post/view");
        return model;
    }

    @RequestMapping(value = "/post/vote", method = RequestMethod.POST)
    public @ResponseBody
    ScoreDTO vote(@RequestParam(value = "id") String id) {
        User user = userService.getCurrentUser();
        Post post = postRepository.findOne(Integer.parseInt(id));

        Vote vote = voteRepository.findByUserAndPost(user, post);
        if(vote == null && !user.equals(post.getUser())){
            vote = new Vote();
            vote.setUser(user);
            vote.setPost(post);

            post.setScore(post.getScore() + 1);

            voteRepository.save(vote);
            postRepository.save(post);
        }

        return new ScoreDTO(post.getScore());
    }
}
