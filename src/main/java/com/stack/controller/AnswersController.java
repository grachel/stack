package com.stack.controller;

import com.stack.model.entities.Answer;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.answer.AnswerRepository;
import com.stack.model.repo.post.PostRepository;
import com.stack.model.repo.user.UserRepository;
import com.stack.model.repo.vote.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class AnswersController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    VoteRepository voteRepository;

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public ModelAndView answer(@RequestParam(value = "postid") String postid,
                     @RequestParam(value = "body") String body) {

        Answer answer = new Answer();
        answer.setUser(userRepository.getCurrentUser());
        answer.setBody(body);
        answer.setCreationDate(new Timestamp(System.currentTimeMillis()));
        answer.setPost(postRepository.findOne(Integer.parseInt(postid)));

        answerRepository.save(answer);

        return new ModelAndView("redirect:/post/" + postid);
    }

    @RequestMapping(value = "/answer/vote", method = RequestMethod.POST)
    public @ResponseBody
    Integer vote(@RequestParam(value = "id") String id) {
        User user = userRepository.getCurrentUser();

        Answer answer = answerRepository.findOne(Integer.parseInt(id));

        Vote vote = voteRepository.findByUserAndAnswer(user, answer);
        if(vote == null && !user.equals(answer.getUser())){
            vote = new Vote();
            vote.setUser(user);
            vote.setAnswer(answer);

            answer.setScore(answer.getScore() + 1);

            voteRepository.save(vote);
            answerRepository.save(answer);
        }

        return answer.getScore();
    }

}
