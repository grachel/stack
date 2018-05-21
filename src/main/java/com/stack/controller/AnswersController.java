package com.stack.controller;

import com.stack.dto.PostDTO;
import com.stack.dto.mappers.PostMapper;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Post;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.answer.AnswerRepository;
import com.stack.model.repo.post.PostRepository;
import com.stack.model.repo.user.UserRepository;
import com.stack.model.repo.vote.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@CrossOrigin(origins = { "http://localhost:3000" })
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
    public @ResponseBody
    PostDTO answer(@RequestParam(value = "postid") String postid,
                   @RequestParam(value = "body") String body) {
        Post parent = postRepository.findOne(Integer.parseInt(postid));

        Answer answer = new Answer();
        answer.setUser(userRepository.getCurrentUser());
        answer.setBody(body);
        answer.setCreationDate(new Timestamp(System.currentTimeMillis()));
        answer.setPost(parent);

        answerRepository.save(answer);

        return PostMapper.INSTANCE.postToPostDTO(parent);
    }

    @RequestMapping(value = "/answer/vote", method = RequestMethod.POST)
    public @ResponseBody
    Integer vote(@RequestParam(value = "id") String id) {
        User user = userRepository.getCurrentUser();

        Answer answer = answerRepository.findOne(Integer.parseInt(id));

        Vote vote = voteRepository.findByUserAndAnswer(user, answer);
        if (vote == null && !user.equals(answer.getUser())) {
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
