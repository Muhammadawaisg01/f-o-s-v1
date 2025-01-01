
package com.fossm.userservice.notifications;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.userservice.model.User;
import com.fossm.userservice.repository.UserRepository;
import com.fossm.userservice.request_filter.AuthFilter;

import jakarta.transaction.Transactional;



@Service
public class Notification_Preference_Service {


    @Autowired
    private Notification_Preference_Repository preference_repository;

    @Autowired
    private NotificationRepository notification_repository;

    @Autowired
    private UserRepository user_repository;



    @Transactional
    int update_preference(UUID userId, UUID typeId, boolean isEnabled) {
        
        return preference_repository.updatePreference(userId, typeId, isEnabled);
    }

    @Transactional
    public int toggleNotifications(UUID userId, boolean isEnabled) {
        
        return preference_repository.toggleNotifications(userId, isEnabled);

    }

    ResponseEntity<String> save_initial_preferences(UUID userId) {
        
        List<Notification_Type> list = notification_repository.findAll();

        Optional<User> user = user_repository.findById(userId);

        for (Notification_Type notification_Type : list) {

            // UUID typeId = notification_Type.getId();
            
            Notification_Preference initial_preference = new Notification_Preference();

            initial_preference.setNotification(notification_Type);
            initial_preference.setEnabled(true);
            initial_preference.setUser(user.get());

            preference_repository.save(initial_preference);
            System.out.println("I am the initial preferences    "+initial_preference);

        }
        return ResponseEntity.ok("Initial Preferences are saved successfully");
    }


    Notification_Preference get_First_Preference(UUID userId){
        
        return preference_repository.findFirstByUserId(userId);
    }
    
    Notification_Type get_Notification_by_Name(ENotification notification){
        
        return notification_repository.findByNotification(notification);
    }

    List<Notification_Preference> get_All_Notification_Preferences(UUID userId){
        
        return preference_repository.findByUserId(userId);
    }
}
