package com.stack.dto.mappers;

import com.stack.dto.CommentDTO;
import com.stack.model.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({
        @Mapping(source = "user.login", target = "user")
    })
    CommentDTO commentToCommentDTO(Comment comment);
}
