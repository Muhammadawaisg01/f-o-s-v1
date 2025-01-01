
package com.fossm.userservice.notifications;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.userservice.exception.BadRequestException;
import com.fossm.userservice.request_filter.AuthFilter;


@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    
    @Autowired
    Notification_Preference_Service preference_service;

    private final UserContextHolder userContextHolder = AuthFilter.userContextHolder;

    // @GetMapping("/{userId}")
    // public ResponseEntity<List<NotificationPreference>> getUserPreferences(@PathVariable Long userId) {
    //     return ResponseEntity.ok(preferenceService.getUserPreferences(userId));
    // }

    @PostMapping("/{userId}/{typeId}")
    public ResponseEntity<String> updateUserPreference(@PathVariable UUID userId, @PathVariable UUID typeId,
                                                       @RequestParam boolean isEnabled) {
                
        if(preference_service.get_First_Preference(userId) == null){
            preference_service.save_initial_preferences(userId);
        }
        
        preference_service.update_preference(userId, typeId, isEnabled);
        return ResponseEntity.ok("Preference updated successfully");
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> toggleNotifications(@PathVariable UUID userId, @RequestParam boolean isEnabled) {

        if(preference_service.get_First_Preference(userId) == null){
            preference_service.save_initial_preferences(userId);
        }

        preference_service.toggleNotifications(userId, isEnabled);
        
        if(isEnabled){
            return ResponseEntity.ok("All notifications are on successfully");
        }else{
            return ResponseEntity.ok("All notifications are off successfully");
        }
    }

    @PostMapping
    public ResponseEntity< Notification_Type > getNotificationIdByName(@RequestParam String notification_name){
        
        Notification_Type notification_Type = preference_service.get_Notification_by_Name(ENotification.valueOf(notification_name.toUpperCase()));
        if(notification_Type != null){
            return ResponseEntity.ok(notification_Type);
        // .orElseThrow(() -> {
        //   var msg = "Unable to retrieve 'username' from context.";
        //   log.error(msg);
        //   return new BadRequestException(msg);
        // });
        }
        else{
            return null;
            // ResponseEntity. notFound() ;
        }
    }

    @GetMapping("/getAll")
    public List<Notification_Preference> getAllNotification_Preferences(){
        
        return preference_service.get_All_Notification_Preferences(userContextHolder.getId().get());
    }



    // @PostMapping("/initial_preferences")
    // public ResponseEntity<String> AddPreference() {

    //     Optional<UUID> userId = userContextHolder.getId();

    //     return preference_service.save_initial_preferences(userId.get());

    // }

}
