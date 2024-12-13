package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.CommentReaction;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, UUID> {

}
