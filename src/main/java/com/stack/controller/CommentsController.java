package com.stack.controller;

import com.stack.model.dao.Comment;
import com.stack.model.dao.Post;
import com.stack.model.dao.User;
import com.stack.model.dao.Vote;
import com.stack.model.entities.CommentariesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentsController {

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public @ResponseBody
    CommentariesEntity add(@RequestParam(value = "postid") String postid,
                           @RequestParam(value = "body") String body) {

        Post post = new Post(postid);
        CommentariesEntity comment = post.addComment(body);
        post.save();

        return comment;
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public Integer score(@RequestParam(value = "id") String id) {

        User currUser =  User.getCurrentUser();

        Comment comment = new Comment(currUser.session);
        comment.findById(id);

        if(comment.getUser().getId() != currUser.getId()){
            List<Vote> votes = comment.getVotes();
            boolean update = true;
            for(Vote vote : votes){
                if(vote.getUser().getId() ==  currUser.getId()){
                    update = false;
                    break;
                }
            }

            if(update){
                comment.incScore();
                Vote vote = new Vote();
                vote.setUser(currUser);
                vote.setComment(comment);
            }
        }

        comment.save();
        return comment.getScore();
    }
}
