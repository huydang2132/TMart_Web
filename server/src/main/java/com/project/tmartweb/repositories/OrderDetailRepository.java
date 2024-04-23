package com.project.tmartweb.repositories;

import com.project.tmartweb.domain.entities.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    Page<OrderDetail> findAllByOrderId(UUID orderId, Pageable pageable);

    List<OrderDetail> findAllByOrderId(UUID orderId);
}
