package com.fossm.contentservice.model;

import com.fossm.contentservice.model.enums.MediaType;
import com.fossm.database.model.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "moments")
public class Moment extends AbstractAuditableEntity {

  @EqualsAndHashCode.Include
  UUID userId;

  @Enumerated(EnumType.STRING)
  MediaType mediaType;

  @EqualsAndHashCode.Include
  String mediaUrl;

  Long viewCount;

  LocalDateTime expirationDate;
}
