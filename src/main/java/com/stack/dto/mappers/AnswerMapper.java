package com.stack.dto.mappers;

import com.stack.dto.AnswerDTO;
import com.stack.model.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { UserMapper.class, PostMapper.class,  CommentMapper.class, VoteMapper.class })
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class );

    AnswerDTO answerToAnswerDTO(Answer answer);
}
