package org.example.server.repository;

import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EPColdWarehouseRepository extends JpaRepository<EnterprisePointColdWarehouse, Integer> {

    @Query(value = "select * from enterprise_point_coldwarehouse where enterprise_point_coldwarehouse.ep_id = ?1", nativeQuery = true)
    List<EnterprisePointColdWarehouse> findAllByEnterprisePoint_IdEquals(Integer EPId);
}
