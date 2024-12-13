package com.fossm.contentservice.mapper;

import com.fossm.contentservice.dto.CommentDto;
import com.fossm.contentservice.model.Comment;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {CommentReactionMapper.class})
public interface CommentMapper {

  Comment toEntity(CommentDto commentDto);

  CommentDto toDto(Comment comment);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);
}
