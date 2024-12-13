package com.fossm.userservice.service;

import com.fossm.userservice.model.Connection;
import com.fossm.userservice.repository.ConnectionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionService {

  private final ConnectionRepository repository;

  public Connection save(Connection connection) {
    return repository.save(connection);
  }

  public Optional<Connection> getConnection(UUID userId, UUID connectionId) {
    return repository.findByUserIdAndConnectedId(userId, connectionId);
  }

  public void delete(UUID userId, UUID connectionId) {
    repository.deleteByUserIdAndConnectedId(userId, connectionId);
  }

  public Page<Connection> getConnections(UUID connectedId, Pageable pageable) {
    return repository.findAllByConnectedId(connectedId, pageable);
  }

  public Page<Connection> getConnected(UUID userId, Pageable pageable) {
    return repository.findAllByUserIdAndPendingFalse(userId, pageable);
  }

  public List<Connection> getConnected(UUID userId, List<UUID> ids) {
    return repository.findAllByUserIdAndConnectedIdIn(userId, ids);
  }
}
