

package com.fossm.userservice.notifications;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import feign.Param;


@Repository
public interface Notification_Preference_Repository extends JpaRepository<Notification_Preference, UUID>{


//     Notification_Preference findByUserIdAndType_id(UUID user_id , UUID type_id);


//     Notification_Preference savePreference(UUID userId, UUID typeId, boolean isEnabled);

    
    List<Notification_Preference> findByUserId(UUID userId);

    Notification_Preference  findFirstByUserId(UUID userId);



    @Modifying
    @Query(nativeQuery = true,
    value="""
            update user_notification_preferences set is_enabled = :isEnabled where user_id = :userId and type_id = :typeId
            """
    )
    int updatePreference(@Param("userId")     UUID userId, 
                         @Param("typeId")     UUID typeId, 
                         @Param("isEnabled")  boolean isEnabled);


    @Modifying
    @Query(nativeQuery = true,
    value="""
            update user_notification_preferences set is_enabled = :isEnabled where user_id = :userId
            """
    )
    int toggleNotifications(@Param("userId") UUID userId, 
                                                      @Param("isEnabled") boolean isEnabled );
        

}

// @Query(nativeQuery = true,
// value = """ 
//     select p.* from posts p join purchased_post pp on pp.post_id=p.id 
//     where pp.user_id=?1 and p.user_id!=?1
//     """)
// Page<Post> findPurchasedPosts(UUID userId, Pageable pageable);

// @Query(value = """
// SELECT p from Post p
// JOIN FETCH p.hashtags ht
// JOIN FETCH p.contents
// JOIN FETCH p.categories
// WHERE LOWER(ht) = LOWER(:hashtag)
// """)
// Page<Post> findPostsByHashtag(String hashtag, Pageable pageable);

// @Query(
// nativeQuery = true,
// value = """
//     SELECT DISTINCT ht.hashtag FROM posts_hashtags ht
//     WHERE LOWER(ht.hashtag) LIKE LOWER(CONCAT(:tag, '%'))
//     """
// )
// Set<String> searchHashtags(String tag);
