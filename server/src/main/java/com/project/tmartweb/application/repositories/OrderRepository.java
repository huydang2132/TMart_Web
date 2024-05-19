package com.project.tmartweb.application.repositories;

import com.project.tmartweb.application.responses.Statistical;
import com.project.tmartweb.domain.entities.Order;
import com.project.tmartweb.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT ord from Order ord inner join OrderDetail od " +
            "on ord.id = od.order.id where ord.user.id = :userId " +
            "and ((ord.status = :status or :status is null) and " +
            "(lower(od.product.title) like lower(concat('%', :keyword, '%')) " +
            "or :keyword is null)) order by ord.createdAt desc")
    List<Order> findByUserId(@Param("userId") UUID userId,
                             @Param("status") OrderStatus status,
                             @Param("keyword") String keyword);

    @Query("select ord from Order ord ")
    List<Statistical> statisticals(int month, int year);
}
