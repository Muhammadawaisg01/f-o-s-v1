package com.fossm.userservice.util;

import com.fossm.userservice.dto.RoleDto;
import com.fossm.userservice.dto.SettingDto;
import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.dto.request.StatusUpdateRequest;
import com.fossm.userservice.dto.request.UserReportRequest;
import com.fossm.userservice.model.Role;
import com.fossm.userservice.model.Setting;
import com.fossm.userservice.model.User;
import com.fossm.userservice.model.UserReport;
import com.fossm.userservice.model.enums.RoleName;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;
import java.util.UUID;
import lombok.experimental.UtilityClass;

import static com.fossm.userservice.model.enums.ReportReason.SPAM;
import static com.fossm.userservice.model.enums.UserSetting.RECEIVE_NEWSLETTERS;

@UtilityClass
public class PredefinedEntities {

  public static final UUID DEFAULT_UUID = UUID.randomUUID();

  public static final Role VIEWER_ROLE = Role.builder()
      .id(DEFAULT_UUID)
      .name(RoleName.VIEWER)
      .build();

  public static final RoleDto VIEWER_ROLE_DTO = RoleDto.builder()
      .name(RoleName.VIEWER)
      .build();

  public static final Role CONTENT_CREATOR_ROLE = Role.builder()
      .id(DEFAULT_UUID)
      .name(RoleName.CONTENT_CREATOR)
      .build();

  public static final RoleDto CONTENT_CREATOR_ROLE_DTO = RoleDto.builder()
      .name(RoleName.CONTENT_CREATOR)
      .build();

  public static final Role UPCOMING_CONTENT_CREATOR_ROLE = Role.builder()
      .id(DEFAULT_UUID)
      .name(RoleName.UPCOMING_CONTENT_CREATOR)
      .build();

  public static final RoleDto UPCOMING_CONTENT_CREATOR_ROLE_DTO = RoleDto.builder()
      .name(RoleName.UPCOMING_CONTENT_CREATOR)
      .build();

  public static final Setting SETTING = Setting.builder()
      .id(DEFAULT_UUID)
      .userSetting(RECEIVE_NEWSLETTERS)
      .value("Some value")
      .enabled(true)
      .build();

  public static final SettingDto SETTING_DTO = SettingDto.builder()
      .id(DEFAULT_UUID)
      .userSetting(RECEIVE_NEWSLETTERS)
      .value("Some value")
      .enabled(true)
      .build();

  public static final User USER = User.builder()
      .id(DEFAULT_UUID)
      .firstName("TestFN")
      .lastName("TestLN")
      .email("test@email.com")
      .username("testUsername")
      .profileAvatarUrl("http://test.image/url")
      .roles(Set.of(VIEWER_ROLE))
      .version(0L)
      .statusText("Status")
      .statusExpirationDate(LocalDateTime.of(2023, Month.JULY, 16, 0, 0).plusDays(1))
      .build();

  public static final UserProfileDto USER_DTO = UserProfileDto.builder()
      .id(DEFAULT_UUID)
      .firstName("TestFN")
      .lastName("TestLN")
      .username("testUsername")
      .profileAvatarUrl("http://test.image/url")
      .roles(Set.of(VIEWER_ROLE_DTO))
      .statusText("Status")
      .statusFileId(DEFAULT_UUID.toString())
      .statusExpirationDate(LocalDateTime.of(2023, Month.JULY, 16, 0, 0).plusDays(1))
      .build();

  public static final StatusUpdateRequest STATUS_UPDATE_REQUEST = StatusUpdateRequest.builder()
      .statusText("Updated status")
      .statusFileId(DEFAULT_UUID.toString())
      .statusExpirationDate(LocalDateTime.of(2023, Month.AUGUST, 16, 0, 0).plusDays(1))
      .build();

  public static final UserReport USER_REPORT_SPAM = UserReport.builder()
      .id(DEFAULT_UUID)
      .reporterId(UUID.randomUUID())
      .reportedId(UUID.randomUUID())
      .reason(SPAM)
      .explanation("Spam")
      .build();

  public static final UserReportRequest USER_REPORT_DTO_SPAM = UserReportRequest.builder()
      .reporterId(UUID.randomUUID())
      .reportedId(UUID.randomUUID())
      .reason(SPAM)
      .explanation("Spam")
      .build();
}