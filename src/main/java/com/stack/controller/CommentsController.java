package com.stack.controller;

import com.stack.model.dto.ScoreDTO;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class CommentsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/comment/post", method = RequestMethod.POST)
    public ModelAndView postComment(@RequestParam(value = "postid") String postid,
                                    @RequestParam(value = "body") String body) {

        if (!StringUtils.isEmpty(body)) {
            Comment comment = new Comment();
            comment.setBody(body);
            comment.setPost(databaseService.findPost(Integer.parseInt(postid)));
            comment.setUser(databaseService.getCurrentUser());
            comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
            databaseService.save(comment);
        }

        return new ModelAndView("redirect:/post/" + postid);
    }

    @RequestMapping(value = "/comment/answer", method = RequestMethod.POST)
    public ModelAndView answerComment(@RequestParam(value = "answerid") String answerid,
                   @RequestParam(value = "body") String body) {

        Answer answer = databaseService.findAnswer(Integer.parseInt(answerid));

        if (!StringUtils.isEmpty(body)) {
            Comment comment = new Comment();
            comment.setBody(body);
            comment.setAnswer(answer);
            comment.setUser(databaseService.getCurrentUser());
            comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
            databaseService.save(comment);
        }

        return new ModelAndView("redirect:/post/" + answer.getPost().getId());
    }

    @RequestMapping(value = "/comment/vote", method = RequestMethod.POST)
    public @ResponseBody
    ScoreDTO vote(@RequestParam(value = "id") String id) {
        User user = databaseService.getCurrentUser();
        Comment comment = databaseService.findComment(Integer.parseInt(id));

        Vote vote = databaseService.findVoteByUserAndComment(user, comment);
        if(vote == null && !user.equals(comment.getUser())){
            vote = new Vote();
            vote.setUser(user);
            vote.setComment(comment);

            comment.setScore(comment.getScore() + 1);

            databaseService.save(vote);
            databaseService.save(comment);
        }

        return new ScoreDTO(comment.getScore());
    }
}

