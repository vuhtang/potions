package org.example.server.repository;

import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPColdWarehouseRepository extends JpaRepository<EnterprisePointColdWarehouse, Integer> {
}
