package com.stack.dto.mappers;

import com.stack.dto.VoteDTO;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { PostMapper.class, CommentMapper.class,  AnswerMapper.class, UserMapper.class})
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper( VoteMapper.class );

    VoteDTO voteToVoteDTO(Vote vote);
}
