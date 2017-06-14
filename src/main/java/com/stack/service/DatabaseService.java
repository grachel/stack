package com.stack.service;

import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.IEntity;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import com.stack.model.repo.AnswerRepository;
import com.stack.model.repo.CommentRepository;
import com.stack.model.repo.PostRepository;
import com.stack.model.repo.TagRepository;
import com.stack.model.repo.UserRepository;
import com.stack.model.repo.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Service
public class DatabaseService {

    private HashMap<Class, CrudRepository> repositoryHashMap = new HashMap<>();

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    TagRepository tagRepository;

    @PostConstruct
    private void init(){
        repositoryHashMap.put(Post.class, postRepository);
        repositoryHashMap.put(User.class, userRepository);
        repositoryHashMap.put(Comment.class, commentRepository);
        repositoryHashMap.put(Answer.class, answerRepository);
        repositoryHashMap.put(Vote.class, voteRepository);
        repositoryHashMap.put(Tag.class, tagRepository);
    }

    public void save(IEntity  entity)
    {
        repositoryHashMap.get(entity.getClass()).save(entity);
    }

    public User findUserByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(username);
    }

    public Post findPost(int id) {
        return postRepository.findOne(id);
    }

    public Answer findAnswer(int id) {
        return answerRepository.findOne(id);
    }

    public Vote findVoteByUserAndAnswer(User user, Answer answer) {
        return voteRepository.findByUserAndAnswer(user, answer);
    }

    public Vote findVoteByUserAndComment(User user, Comment comment) {
        return voteRepository.findByUserAndComment(user,comment);
    }

    public Comment findComment(int id) {
        return commentRepository.findOne(id);
    }

    public Tag findTag(String t) {
        return tagRepository.findByTag(t);
    }

    public Iterable<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Vote findVoteByUserAndPost(User user, Post post) {
        return voteRepository.findByUserAndPost(user, post);
    }
}
