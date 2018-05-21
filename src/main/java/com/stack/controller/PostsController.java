package com.stack.controller;

import com.stack.dto.PostDTO;
import com.stack.dto.mappers.PostMapper;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.post.PostRepository;
import com.stack.model.repo.tag.TagRepository;
import com.stack.model.repo.user.UserRepository;
import com.stack.model.repo.vote.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000" })
@Controller
public class PostsController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    List<PostDTO> home(@RequestParam(value = "t", required=false) String t) { // TODO : use t as tag search
        return PostMapper.INSTANCE.mapCollection(postRepository.findAll());
    }

    @RequestMapping(value = "/post/ask", method = RequestMethod.POST)
    public @ResponseBody String ask(@RequestParam(value = "title") String title,
                            @RequestParam(value = "body") String body,
                            @RequestParam(value = "tags") String tags) {
        String error = "";
        try {
            Post post = new Post();
            post.setUser(userRepository.getCurrentUser());
            post.setTitle(title);
            post.setBody(body);
            post.setCreationDate(new Timestamp(System.currentTimeMillis()));
            post.setTags(new HashSet<>());

            String[] splitted = tags.split(" ");

            for (String tag : splitted) {
                Tag t = tagRepository.findByTag(tag);
                if (t == null) {
                    t = new Tag();
                    t.setTag(tag);

                    tagRepository.save(t);
                }

                post.getTags().add(t);
            }

            postRepository.save(post);
        } catch (Exception e){
            error = e.getMessage();
        }
        return error;
    }

    @RequestMapping(value = "post/my", method = RequestMethod.GET)
    public @ResponseBody List<PostDTO> myPosts() {
        return PostMapper.INSTANCE.mapCollection(userRepository.getCurrentUser().getPosts());
    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.GET)
    public @ResponseBody PostDTO view(@PathVariable String id) {
        return PostMapper.INSTANCE.postToPostDTO(postRepository.findOne(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/post/vote", method = RequestMethod.POST)
    public @ResponseBody
    Integer vote(@RequestParam(value = "id") String id) {
        User user = userRepository.getCurrentUser();
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

        return post.getScore();
    }
}
