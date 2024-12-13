package com.fossm.contentservice.util;

import com.fossm.contentservice.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import lombok.experimental.UtilityClass;

import static com.fossm.contentservice.model.enums.MediaType.IMAGE;
import static com.fossm.contentservice.model.enums.RelationType.EVERYONE;
import static java.time.Month.AUGUST;

@UtilityClass
public class PredefinedEntities {

  public static final UUID DEFAULT_UUID = UUID.fromString("7dfca759-8184-4f00-b27e-3a9a44959477");
  public static final String ACCESS_TOKEN = "eyJraWQiOiJ1c1FYZlZSRWNYbXRsbzU5cWhaU0VVZmZcL1wvVlBoMlNLN1VNbmE5R2tCV0E9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI3ZGZjYTc1OS04MTg0LTRmMDAtYjI3ZS0zYTlhNDQ5NTk0NzciLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuZXUtY2VudHJhbC0xLmFtYXpvbmF3cy5jb21cL2V1LWNlbnRyYWwtMV9wTHFZTlRGTHYiLCJjbGllbnRfaWQiOiI1aWxlYnFsZGRiMTExZGUwdnVwZzJ2NWM4bCIsIm9yaWdpbl9qdGkiOiJlZDNjZjcxOC0yNDkwLTQzZTYtOTVjNS0wMzdjZjRhMTNiMTciLCJldmVudF9pZCI6ImE3ODE2MmVmLTIzYTEtNGZjMC1hMzI3LWMyYzJhMDc0YjVlYiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2OTkzNjkxODUsImV4cCI6MTY5OTM3Mjc4NSwiaWF0IjoxNjk5MzY5MTg1LCJqdGkiOiI1ZDg3NTY0MC00YmRhLTQxYjktYmZkZS0wODFiMTEzYjNhZGEiLCJ1c2VybmFtZSI6ImFsbGEifQ.sL_Val16va5hoftyiCqGWYXwb-13FxEVDRchkSYLxGn5MTFpVgIIA-F0meWALX4ugWy67-dKSXb6qg6fLPmO1-lw7JE8eocJAsmyhg-hxCpoHvdtfS4gKXTidF72jC_5IndFvBaRELzJAa9KxCeCtRnADE7mzb_kAPmJqjbXknaCkVCJ2lYSqoow2mp50BcoEVsYv3Sf31TVc45Q6QhA6--RTu32RJcd6HzzLfG4wP28mdnfjtmxCzD2L8Qh6yHZKDTx5mZ9_LiT1L6gqjwEpSDpZUyUW5-_a9suz8Ql-O0VYV5786DLqqOx3CZsEiBuAAgpEQrGRf8IFZmCpCbjHw";

  public static final Post POST = Post.builder()
      .id(DEFAULT_UUID)
      .version(0L)
      .createdAt(LocalDateTime.of(2023, AUGUST, 18, 0, 0))
      .updatedAt(LocalDateTime.of(2023, AUGUST, 18, 0, 0))
      .description("description")
      .userId(DEFAULT_UUID)
      .mediaType(IMAGE)
      .relationType(EVERYONE)
      .viewCount(0L)
      .reactions(Set.of())
      .contents(new ArrayList<>())
      .build();
}
