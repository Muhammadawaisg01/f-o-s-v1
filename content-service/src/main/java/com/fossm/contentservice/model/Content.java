package com.fossm.contentservice.model;

import com.fossm.database.model.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "contents")
public class Content extends AbstractEntity {

  @EqualsAndHashCode.Include
  String thumbnailKey;

  @EqualsAndHashCode.Include
  String thumbnailBlurKey;

  @EqualsAndHashCode.Include
  String mediaKey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @ToString.Exclude
  Post post;
}
