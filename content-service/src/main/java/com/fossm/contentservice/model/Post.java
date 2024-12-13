package com.fossm.contentservice.model;

import com.fossm.contentservice.model.converter.ContentWarningConverter;
import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.contentservice.model.enums.ContentWarning;
import com.fossm.contentservice.model.enums.MediaType;
import com.fossm.contentservice.model.enums.RelationType;
import com.fossm.database.model.AbstractAuditableEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Entity
@Table(name = "posts")
@Where(clause = "deleted = false")
public class Post extends AbstractAuditableEntity {

  UUID userId;

  String username;

  @Enumerated(EnumType.STRING)
  MediaType mediaType;

  String description;

  @Enumerated(EnumType.STRING)
  RelationType relationType;

  boolean ageLimited;

  @Convert(converter = ContentWarningConverter.class)
  private Set<ContentWarning> warnings;

  /*Double Monetization*/
  boolean doubleMonetization;

  /*Double Monetization*/
  BigDecimal price;

  long viewCount;

  Boolean allowComments;

  boolean deleted;

  @ElementCollection(targetClass = ContentCategory.class)
  @CollectionTable(name = "posts_content_categories", joinColumns = @JoinColumn(name = "post_id"))
  @Column(name = "content_category_name")
  @Enumerated(EnumType.STRING)
  @ToString.Exclude
  Set<ContentCategory> categories;

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "posts_hashtags", joinColumns = @JoinColumn(name = "post_id"))
  @Column(name = "hashtag")
  @ToString.Exclude
  Set<String> hashtags;

  @OneToMany(mappedBy = "post", orphanRemoval = true)
  @ToString.Exclude
  Set<PostReaction> reactions;

  @OneToMany(mappedBy = "post", orphanRemoval = true)
  @ToString.Exclude
  Set<Comment> comments;

  @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
  @ToString.Exclude
  List<Content> contents;

}
