package com.fossm.contentservice.model;

import com.fossm.database.model.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comment_reactions")
public class CommentReaction extends AbstractAuditableEntity {

  @EqualsAndHashCode.Include
  UUID userId;

  String username;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id")
  @ToString.Exclude
  Comment comment;
}
