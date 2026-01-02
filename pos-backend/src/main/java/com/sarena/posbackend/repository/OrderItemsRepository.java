package com.sarena.posbackend.repository;

import com.sarena.posbackend.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

    // 🔢 total price of an order
    @Query("""
        SELECT COALESCE(SUM(oi.lineTotal), 0)
        FROM OrderItems oi
        WHERE oi.order.id = :orderId
    """)
    BigDecimal sumTotalByOrderId(@Param("orderId") Long orderId);

    // 💰 unpaid balance of an order
    @Query("""
        SELECT COALESCE(SUM(oi.unpaid), 0)
        FROM OrderItems oi
        WHERE oi.order.id = :orderId
    """)
    BigDecimal sumUnpaidByOrderId(@Param("orderId") Long orderId);

    // 📦 get all items of an order (detail page)
    List<OrderItems> findByOrder_Id(Long orderId);
}
