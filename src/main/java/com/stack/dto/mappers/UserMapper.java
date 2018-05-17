package com.stack.dto.mappers;

import com.stack.dto.AnswerDTO;
import com.stack.dto.CommentDTO;
import com.stack.dto.PostDTO;
import com.stack.dto.UserDTO;
import com.stack.dto.VoteDTO;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.Post;
import com.stack.model.entities.User;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class UserMapper {

    public static UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    abstract UserDTO userToUserDTOWithoutRelations(User user);

    public UserDTO userToUserDTO(User source){
        UserDTO ret = userToUserDTOWithoutRelations(source);
        setPosts(source, ret);
        setComments(source, ret);
        setAnswers(source, ret);
        setVotes(source, ret);
        return ret;
    }

    public void setPosts(User source, @MappingTarget UserDTO target) {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : source.getPosts()){
            postDTOS.add(PostMapper.INSTANCE.postToPostDTOWithoutRelations(post));
        }
        target.setPosts(postDTOS);
    }

    public void setAnswers(User source, @MappingTarget UserDTO target) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer answer : source.getAnswers()){
            answerDTOS.add(AnswerMapper.INSTANCE.answerToAnswerDTO(answer));
        }
        target.setAnswers(answerDTOS);
    }

    public void setComments(User source, @MappingTarget UserDTO target) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : source.getComments()){
            commentDTOS.add(CommentMapper.INSTANCE.commentToCommentDTOWithoutRelations(comment));
        }
        target.setComments(commentDTOS);
    }

    public void setVotes(User source, @MappingTarget UserDTO target) {
        List<VoteDTO> votesDTOS = new ArrayList<>();
        for(Vote vote : source.getVotes()){
            votesDTOS.add(VoteMapper.INSTANCE.voteToVoteDTOWithoutRelations(vote));
        }
        target.setVotes(votesDTOS);
    }
}