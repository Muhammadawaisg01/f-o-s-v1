package com.fossm.userservice.model;

import com.fossm.database.model.AbstractAuditableEntity;
import com.fossm.userservice.model.enums.ReportReason;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_reports")
public class UserReport extends AbstractAuditableEntity {

  UUID reporterId;

  UUID reportedId;

  UUID postId;

  @Enumerated(EnumType.STRING)
  ReportReason reason;

  String explanation;

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "screenshot_upload_ids", joinColumns = @JoinColumn(name = "user_report_id"))
  @Column(name = "screenshot_upload_id", nullable = false)
  List<String> screenshotUploadIds;

}