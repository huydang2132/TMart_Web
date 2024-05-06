package com.project.tmartweb.application.repositories;

import com.project.tmartweb.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByTitle(String title);

    Page<Product> findAllByCategory_IdAndDeleted(UUID id, boolean deleted, Pageable pageable);

    Page<Product> findAllByDeleted(boolean deleted, Pageable pageable);

    List<Product> findAllByDeleted(boolean deleted, Sort sort);

    List<Product> findAllByCategory_Id(UUID id);

    @Query("select p from Product p left join Feedback f on p.id = f.product.id " +
            "where p.deleted = false " +
            "and (lower(p.title) like lower(concat('%', :keyword, '%')) or :keyword is null) " +
            "group by p.id " +
            "order by CASE WHEN :feedback = 'asc' THEN COALESCE(avg(f.star), 0) END ASC, " +
            "CASE WHEN :feedback = 'desc' THEN COALESCE(avg(f.star), 0) END DESC NULLS LAST, " +
            "CASE WHEN :price = 'asc' THEN p.price END ASC, " +
            "CASE WHEN :price = 'desc' THEN p.price END DESC NULLS LAST ")
    Page<Product> findAllBySearch(String keyword, String feedback, String price, Pageable pageable);

    @Query("select pr " +
            "from Product pr inner join OrderDetail od on pr.id = od.product.id " +
            "where pr.deleted = false group by pr.id order by count(od.id) desc")
    Page<Product> findAllBySoldQuantity(Pageable pageable);

    @Query("select p from Product p where p.discount > 0 " +
            "and p.deleted = false order by p.discount desc")
    Page<Product> findAllByDiscount(Pageable pageable);
}
