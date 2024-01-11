package org.example.server.repository;

import org.example.server.model.enterprise.EnterprisePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EPRepository extends JpaRepository<EnterprisePoint, Integer> {
}
