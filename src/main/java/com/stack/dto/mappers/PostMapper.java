package com.stack.dto.mappers;

import com.stack.dto.PostDTO;
import com.stack.model.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { UserMapper.class, CommentMapper.class,  AnswerMapper.class, VoteMapper.class, TagMapper.class})
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    PostDTO postToPostDTO(Post post);
}
