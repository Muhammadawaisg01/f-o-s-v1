package com.fossm.contentservice.model;

import com.fossm.database.model.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
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
@Table(name = "comments")
public class Comment extends AbstractAuditableEntity {

  @EqualsAndHashCode.Include
  UUID userId;

  @EqualsAndHashCode.Include
  String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @ToString.Exclude
  Post post;

  @OneToMany(mappedBy = "comment", orphanRemoval = true)
  @ToString.Exclude
  Set<CommentReaction> reactions;
}
