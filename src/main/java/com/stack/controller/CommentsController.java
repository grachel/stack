package com.stack.controller;

import com.stack.json.Score;
import com.stack.model.dao.Comment;
import com.stack.model.dao.Post;
import com.stack.model.dao.User;
import com.stack.model.dao.Vote;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentsController {

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public @ResponseBody
    com.stack.json.Comment add(@RequestParam(value = "postid") String postid,
                           @RequestParam(value = "body") String body) {

        if(!StringUtils.isEmpty(body)) {
            Post post = new Post(postid);
            Comment comment = post.addComment(body);
            post.save();
            return new com.stack.json.Comment(comment);
        }
        return null;
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public @ResponseBody
    Score score(@RequestParam(value = "id") String id) {
        User currUser =  User.getCurrentUser();

        Comment comment = new Comment(currUser.session);
        comment.findById(id);

        if(comment.getUser().getId() != currUser.getId()){
            if(!comment.wasVotedByUser(currUser)){
                comment.incScore();
                Vote vote = new Vote(currUser.session);
                vote.setUser(currUser);
                vote.setComment(comment);
                vote.persist();
            }
        }

        comment.save();
        return new Score(comment.getId(), comment.getScore());
    }
}
