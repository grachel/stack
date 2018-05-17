package com.stack.model.repo.vote;

import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.Post;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Integer> {
    Vote findByUserAndPost(User user, Post post);
    Vote findByUserAndAnswer(User user, Answer answer);
    Vote findByUserAndComment(User user, Comment comment);
}
