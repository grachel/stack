package com.stack.dto.mappers;

import com.stack.dto.UserDTO;
import com.stack.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { PostMapper.class, CommentMapper.class,  AnswerMapper.class, VoteMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO userToUserDTO(User user);
}
