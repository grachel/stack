package com.stack.dto.mappers;

import com.stack.dto.CommentDTO;
import com.stack.model.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { UserMapper.class, PostMapper.class,  AnswerMapper.class, VoteMapper.class})
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO commentToCommentDTO(Comment comment);
}
