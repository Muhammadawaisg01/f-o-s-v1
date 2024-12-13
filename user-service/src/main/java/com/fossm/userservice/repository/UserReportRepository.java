package com.fossm.userservice.repository;

import com.fossm.userservice.model.UserReport;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, UUID> {

  UserReport findByReporterIdAndReportedId(UUID reporterId, UUID reportedId);

}
