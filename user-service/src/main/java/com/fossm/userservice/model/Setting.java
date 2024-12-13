package com.fossm.userservice.model;

import com.fossm.database.model.AbstractEntity;
import com.fossm.userservice.model.enums.UserSetting;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "settings")
public class Setting extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  UserSetting userSetting;

  String value;

  boolean enabled;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  User user;

}
