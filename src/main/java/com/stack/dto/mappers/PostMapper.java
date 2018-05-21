package com.stack.dto.mappers;

import com.stack.dto.PostDTO;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = { CommentMapper.class,  AnswerMapper.class })
public abstract class PostMapper {

    public static PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    @Mappings({
        @Mapping(source = "user.login", target = "user"),
        @Mapping(target = "tags", ignore = true)
    })
    public abstract PostDTO postToPostDTO(Post post);

    @AfterMapping
    void setTags(Post source, @MappingTarget PostDTO target){
        StringBuilder tags = new StringBuilder();
        for(Tag tag : source.getTags()){
            tags.append("#").append(tag.getTag()).append(" ");
        }
        target.setTags(tags.toString().trim());
    }

    public List<PostDTO> mapCollection(Iterable<Post> source){
        List<PostDTO> results = new ArrayList<>();
        for(Post post : source){
            results.add(PostMapper.INSTANCE.postToPostDTO(post));
        }
        return results;
    }
}
