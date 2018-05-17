package com.stack.dto.mappers;

import com.stack.dto.PostDTO;
import com.stack.dto.TagDTO;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class TagMapper {

    public static TagMapper INSTANCE = Mappers.getMapper( TagMapper.class );

    @Mapping(target = "posts", ignore = true)
    abstract TagDTO tagToTagDTOWithoutRelations(Tag tag);

    public TagDTO tagToTagDTO(Tag source){
        TagDTO ret = tagToTagDTOWithoutRelations(source);
        setPosts(source, ret);
        return ret;
    }


    @AfterMapping
    public void setPosts(Tag source, @MappingTarget TagDTO target) {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : source.getPosts()){
            postDTOS.add(PostMapper.INSTANCE.postToPostDTOWithoutRelations(post));
        }
        target.setPosts(postDTOS);
    }
}
