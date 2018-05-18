package com.stack.dto.mappers;

import com.stack.dto.TagDTO;
import com.stack.model.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { PostMapper.class })
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper( TagMapper.class );

    TagDTO tagToTagDTO(Tag tag);
}
