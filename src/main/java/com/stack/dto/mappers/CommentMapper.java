package com.stack.dto.mappers;

import com.stack.dto.CommentDTO;
import com.stack.dto.VoteDTO;
import com.stack.model.entities.Comment;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class CommentMapper {

    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "votes", ignore = true)
    abstract CommentDTO commentToCommentDTOWithoutRelations(Comment comment);

    public CommentDTO commentToCommentDTO(Comment source){
        CommentDTO ret = commentToCommentDTOWithoutRelations(source);
        setUser(source, ret);
        setPost(source, ret);
        setAnswer(source, ret);
        setVotes(source, ret);
        return ret;
    }

    public void setPost(Comment source, @MappingTarget CommentDTO target) {
        target.setPost(PostMapper.INSTANCE.postToPostDTOWithoutRelations(source.getPost()));
    }

    public void setAnswer(Comment source, @MappingTarget CommentDTO target) {
        target.setAnswer(AnswerMapper.INSTANCE.answerToAnswerDTO(source.getAnswer()));
    }

    public void setUser(Comment source, @MappingTarget CommentDTO target) {
        target.setUser(UserMapper.INSTANCE.userToUserDTOWithoutRelations(source.getUser()));
    }

    public void setVotes(Comment source, @MappingTarget CommentDTO target) {
        List<VoteDTO> votesDTOS = new ArrayList<>();
        for(Vote vote : source.getVotes()){
            votesDTOS.add(VoteMapper.INSTANCE.voteToVoteDTOWithoutRelations(vote));
        }
        target.setVotes(votesDTOS);
    }
}