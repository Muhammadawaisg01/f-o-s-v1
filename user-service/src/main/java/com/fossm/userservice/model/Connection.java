package com.fossm.userservice.model;

import com.fossm.database.model.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "connections")
public class Connection extends AbstractEntity {

  /*Actor who makes connection*/
  @EqualsAndHashCode.Include
  UUID userId;

  /*Who was connected*/
  @EqualsAndHashCode.Include
  UUID connectedId;

  boolean pending;

}