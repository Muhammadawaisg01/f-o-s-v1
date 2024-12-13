package com.fossm.userservice.service;

import com.fossm.userservice.dto.request.UserReportRequest;
import com.fossm.userservice.mapper.UserReportMapper;
import com.fossm.userservice.model.UserReport;
import com.fossm.userservice.repository.UserReportRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fossm.userservice.util.PredefinedEntities.USER_REPORT_DTO_SPAM;
import static com.fossm.userservice.util.PredefinedEntities.USER_REPORT_SPAM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReportServiceTest {

  @Mock
  private UserReportRepository userReportRepository;
  @Mock
  private UserReportMapper userReportMapper;
  @InjectMocks
  private UserReportService userReportService;

  @Test
  void reportUser() {
    // Setup
    when(userReportMapper.toEntity(any(UserReportRequest.class))).thenReturn(USER_REPORT_SPAM);
    when(userReportRepository.save(any(UserReport.class))).thenReturn(USER_REPORT_SPAM);

    // Run the test
    userReportService.reportUser(USER_REPORT_DTO_SPAM);

    // Verify the results
    verify(userReportRepository).save(USER_REPORT_SPAM);
  }
}