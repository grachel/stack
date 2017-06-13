package com.stack.controller;

import com.stack.model.dto.ScoreDTO;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.AnswerRepository;
import com.stack.model.repo.CommentRepository;
import com.stack.model.repo.PostRepository;
import com.stack.model.repo.VoteRepository;
import com.stack.service.UserService;
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
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    VoteRepository voteRepository;

    @RequestMapping(value = "/comment/post", method = RequestMethod.POST)
    public ModelAndView postComment(@RequestParam(value = "postid") String postid,
                                    @RequestParam(value = "body") String body) {

        if (!StringUtils.isEmpty(body)) {
            Comment comment = new Comment();
            comment.setBody(body);
            comment.setPost(postRepository.findOne(Integer.parseInt(postid)));
            comment.setUser(userService.getCurrentUser());
            comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
            commentRepository.save(comment);
        }

        return new ModelAndView("redirect:/post/" + postid);
    }

    @RequestMapping(value = "/comment/answer", method = RequestMethod.POST)
    public ModelAndView answerComment(@RequestParam(value = "answerid") String answerid,
                   @RequestParam(value = "body") String body) {

        Answer answer = answerRepository.findOne(Integer.parseInt(answerid));

        if (!StringUtils.isEmpty(body)) {
            Comment comment = new Comment();
            comment.setBody(body);
            comment.setAnswer(answer);
            comment.setUser(userService.getCurrentUser());
            comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
            commentRepository.save(comment);
        }

        return new ModelAndView("redirect:/post/" + answer.getPost().getId());
    }

    @RequestMapping(value = "/comment/vote", method = RequestMethod.POST)
    public @ResponseBody
    ScoreDTO vote(@RequestParam(value = "id") String id) {
        User user = userService.getCurrentUser();
        Comment comment = commentRepository.findOne(Integer.parseInt(id));

        Vote vote = voteRepository.findByUserAndComment(user, comment);
        if(vote == null && !user.equals(comment.getUser())){
            vote = new Vote();
            vote.setUser(user);
            vote.setComment(comment);

            comment.setScore(comment.getScore() + 1);

            voteRepository.save(vote);
            commentRepository.save(comment);
        }

        return new ScoreDTO(comment.getScore());
    }
}
