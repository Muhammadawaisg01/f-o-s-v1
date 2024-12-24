
package com.fossm.userservice.notifications;

import java.util.UUID;

import com.fossm.userservice.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="user_notification_preferences",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "type_id"})
        })

public class Notification_Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
    Notification_Type notification;

    boolean isEnabled;

}
