package com.fossm.contentservice.mapper;

import com.fossm.contentservice.dto.MomentDto;
import com.fossm.contentservice.model.Moment;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MomentMapper {

  Moment toEntity(MomentDto momentDto);

  MomentDto toDto(Moment moment);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Moment partialUpdate(MomentDto momentDto, @MappingTarget Moment moment);
}
