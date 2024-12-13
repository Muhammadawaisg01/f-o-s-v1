package com.fossm.userservice.repository;

import com.fossm.userservice.model.Connection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, UUID> {

  Optional<Connection> findByUserIdAndConnectedId(UUID userId, UUID connectedId);

  void deleteByUserIdAndConnectedId(UUID userId, UUID connectedId);

  Page<Connection> findAllByConnectedId(UUID connectedId, Pageable pageable);

  Page<Connection> findAllByUserIdAndPendingFalse(UUID userId, Pageable pageable);

  List<Connection> findAllByUserIdAndConnectedIdIn(UUID userId, List<UUID> ids);
}
