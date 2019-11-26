package com.wee.test.shop.repository;

import com.wee.test.shop.domain.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ShopRepository extends PagingAndSortingRepository<Shop, String> {

    @Query("SELECT s FROM shop s LEFT JOIN FETCH s.items where s.id = :id")
    Shop fetchShopByItem(final String id);

    List<Shop> findAll(Pageable pageable);
}
