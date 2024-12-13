package com.fossm.userservice.mapper;

import com.fossm.userservice.dto.SettingDto;
import com.fossm.userservice.model.Setting;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface SettingMapper {

  Setting toEntity(SettingDto settingDto);

  SettingDto toDto(Setting setting);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Setting partialUpdate(SettingDto settingDto, @MappingTarget Setting setting);

}