package com.fossm.userservice.service;

import com.fossm.userservice.dto.request.UserReportRequest;
import com.fossm.userservice.mapper.UserReportMapper;
import com.fossm.userservice.repository.UserReportRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReportService {

  private final UserReportRepository userReportRepository;
  private final UserReportMapper userReportMapper;

  public void reportUser(UserReportRequest userReportDto) {
    userReportRepository.save(userReportMapper.toEntity(userReportDto));
  }

}
