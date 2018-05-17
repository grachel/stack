package com.stack.dto.mappers;

import com.stack.dto.AnswerDTO;
import com.stack.dto.CommentDTO;
import com.stack.dto.VoteDTO;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class AnswerMapper {

    public static AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class );

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    abstract AnswerDTO answerToAnswerDTOWithoutRelations(Answer answer);

    public AnswerDTO answerToAnswerDTO(Answer answer){
        AnswerDTO ret = answerToAnswerDTOWithoutRelations(answer);
        setUser(answer, ret);
        setPost(answer, ret);
        setComments(answer, ret);
        setVotes(answer, ret);
        return ret;
    }

    public void setUser(Answer source, @MappingTarget AnswerDTO target) {
        target.setUser(UserMapper.INSTANCE.userToUserDTOWithoutRelations(source.getUser()));
    }

    public void setPost(Answer source, @MappingTarget AnswerDTO target) {
        target.setPost(PostMapper.INSTANCE.postToPostDTOWithoutRelations(source.getPost()));
    }

    public void setComments(Answer source, @MappingTarget AnswerDTO target) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : source.getComments()){
            commentDTOS.add(CommentMapper.INSTANCE.commentToCommentDTOWithoutRelations(comment));
        }
        target.setComments(commentDTOS);
    }

    public void setVotes(Answer source, @MappingTarget AnswerDTO target) {
        List<VoteDTO> votesDTOS = new ArrayList<>();
        for(Vote vote : source.getVotes()){
            votesDTOS.add(VoteMapper.INSTANCE.voteToVoteDTOWithoutRelations(vote));
        }
        target.setVotes(votesDTOS);
    }
}
