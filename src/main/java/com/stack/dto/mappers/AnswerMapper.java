package com.stack.dto.mappers;

import com.stack.dto.AnswerDTO;
import com.stack.model.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { CommentMapper.class })
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class );

    @Mappings({
        @Mapping(source = "user.login", target = "user")
    })
    AnswerDTO answerToAnswerDTO(Answer answer);
}
