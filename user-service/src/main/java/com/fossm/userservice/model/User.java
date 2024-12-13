package com.fossm.userservice.model;

import com.fossm.userservice.model.enums.Language;
import com.fossm.userservice.model.enums.TopCreatorMark;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {

  @Id
  @EqualsAndHashCode.Include
  UUID id;

  @Version
  Long version;

  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime updatedAt;

  String email;

  String username;

  @Enumerated(EnumType.STRING)
  Language preferredLanguage;

  String firstName;

  String lastName;

  String profileAvatarUrl;

  String profileCoverUrl;

  String profileDescription;

  String statusText;

  String statusFileId;

  LocalDateTime statusExpirationDate;

  boolean verified;

  boolean isPrivate;

  @Enumerated(EnumType.STRING)
  TopCreatorMark topCreatorMark;

  @OneToMany(mappedBy = "user", orphanRemoval = true)
  @ToString.Exclude
  Set<Setting> settings;

  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  @ToString.Exclude
  Set<Role> roles;

}


