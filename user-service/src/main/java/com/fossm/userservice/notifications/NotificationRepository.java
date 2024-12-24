
package com.fossm.userservice.notifications;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification_Type, UUID>  {

    Notification_Type findNotification_TypeById(UUID id);

    Notification_Type findByNotification(ENotification notification);

    List<Notification_Type> findAll();

}
