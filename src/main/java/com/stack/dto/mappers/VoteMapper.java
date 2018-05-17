package com.stack.dto.mappers;

import com.stack.dto.VoteDTO;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class VoteMapper {

    public static VoteMapper INSTANCE = Mappers.getMapper( VoteMapper.class );

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "user", ignore = true)
    abstract VoteDTO voteToVoteDTOWithoutRelations(Vote vote);

    public VoteDTO voteToVoteDTO(Vote source){
        VoteDTO ret = voteToVoteDTOWithoutRelations(source);
        setPost(source, ret);
        setComment(source, ret);
        setAnswer(source, ret);
        setUser(source, ret);
        return ret;
    }

    public void setPost(Vote source, @MappingTarget VoteDTO target) {
        target.setPost(PostMapper.INSTANCE.postToPostDTOWithoutRelations(source.getPost()));
    }

    public void setAnswer(Vote source, @MappingTarget VoteDTO target) {
        target.setAnswer(AnswerMapper.INSTANCE.answerToAnswerDTO(source.getAnswer()));
    }

    public void setComment(Vote source, @MappingTarget VoteDTO target) {
        target.setComment(CommentMapper.INSTANCE.commentToCommentDTOWithoutRelations(source.getComment()));
    }

    public void setUser(Vote source, @MappingTarget VoteDTO target) {
        target.setUser(UserMapper.INSTANCE.userToUserDTOWithoutRelations(source.getUser()));
    }
}