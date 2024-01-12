package org.example.server.repository;

import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EPWarehouseRepository extends JpaRepository<EnterprisePointWarehouse, Integer> {

    @Query(value = "select * from enterprise_point_warehouse where enterprise_point_warehouse.ep_id = ?1", nativeQuery = true)
    List<EnterprisePointWarehouse> findAllByEnterprisePoint_Id(Integer EPId);
}
