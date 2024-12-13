package com.fossm.fileservice.model;

import com.fossm.database.model.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "file_metadata")
public class FileMetadata extends AbstractAuditableEntity {

  @EqualsAndHashCode.Include
  String uploadId;

  UUID userId;

  String filename;

  String filetype;

  String relativePath;

  String bucket;

}
