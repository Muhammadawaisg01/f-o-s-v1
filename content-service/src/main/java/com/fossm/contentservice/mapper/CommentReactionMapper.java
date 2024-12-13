package com.fossm.contentservice.mapper;

import com.fossm.contentservice.dto.CommentReactionDto;
import com.fossm.contentservice.model.CommentReaction;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface CommentReactionMapper {

  CommentReaction toEntity(CommentReactionDto commentReactionDto);

  CommentReactionDto toDto(CommentReaction commentReaction);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  CommentReaction partialUpdate(CommentReactionDto commentReactionDto, @MappingTarget CommentReaction commentReaction);
}
