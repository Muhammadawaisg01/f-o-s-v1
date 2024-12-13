package com.fossm.userservice.mapper;

import com.fossm.userservice.dto.request.UserReportRequest;
import com.fossm.userservice.model.UserReport;

import org.mapstruct.Mapper;

@Mapper
public interface UserReportMapper {

  UserReport toEntity(UserReportRequest userReportDto);

  UserReportRequest toDto(UserReport userReport);

}