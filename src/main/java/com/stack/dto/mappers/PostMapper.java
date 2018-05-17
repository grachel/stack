package com.stack.dto.mappers;

import com.stack.dto.AnswerDTO;
import com.stack.dto.CommentDTO;
import com.stack.dto.PostDTO;
import com.stack.dto.TagDTO;
import com.stack.dto.VoteDTO;
import com.stack.model.entities.Answer;
import com.stack.model.entities.Comment;
import com.stack.model.entities.Post;
import com.stack.model.entities.Tag;
import com.stack.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class PostMapper {

    public static PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "tags", ignore = true)
    public abstract PostDTO postToPostDTOWithoutRelations(Post post);

    public PostDTO postToPostDTO(Post source){
        PostDTO ret = postToPostDTOWithoutRelations(source);
        setUser(source, ret);
        setComments(source, ret);
        setAnswers(source, ret);
        setVotes(source, ret);
        setTags(source, ret);
        return ret;
    }

    public void setUser(Post source, @MappingTarget PostDTO target) {
        target.setUser(UserMapper.INSTANCE.userToUserDTOWithoutRelations(source.getUser()));
    }

    public void setAnswers(Post source, @MappingTarget PostDTO target) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer answer : source.getAnswers()){
            answerDTOS.add(AnswerMapper.INSTANCE.answerToAnswerDTO(answer));
        }
        target.setAnswers(answerDTOS);
    }

    public void setComments(Post source, @MappingTarget PostDTO target) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : source.getComments()){
            commentDTOS.add(CommentMapper.INSTANCE.commentToCommentDTOWithoutRelations(comment));
        }
        target.setComments(commentDTOS);
    }

    public void setVotes(Post source, @MappingTarget PostDTO target) {
        List<VoteDTO> votesDTOS = new ArrayList<>();
        for(Vote vote : source.getVotes()){
            votesDTOS.add(VoteMapper.INSTANCE.voteToVoteDTOWithoutRelations(vote));
        }
        target.setVotes(votesDTOS);
    }

    public void setTags(Post source, @MappingTarget PostDTO target) {
        List<TagDTO> tagDTOS = new ArrayList<>();
        for(Tag tag : source.getTags()){
            tagDTOS.add(TagMapper.INSTANCE.tagToTagDTOWithoutRelations(tag));
        }
        target.setTags(tagDTOS);
    }
}
