package com.stack.controller;

import com.stack.model.dao.Post;
import com.stack.model.entities.CommentariesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public CommentariesEntity add(@RequestParam(value = "postid") String postid,
                                  @RequestParam(value = "body") String body) {

        Post post = new Post(postid);
        CommentariesEntity comment = post.addComment(body);
        post.save();

        return comment;
    }
}
