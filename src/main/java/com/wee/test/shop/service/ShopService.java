package com.wee.test.shop.service;

import com.wee.test.shop.domain.Shop;
import com.wee.test.shop.dto.ReportDto;
import com.wee.test.shop.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private ShopRepository repository;

    public ShopService(ShopRepository repository) {
        this.repository = repository;
    }

    public ReportDto report(final String shopId) {

        final Shop shop = repository.fetchShopByItem(shopId);

        final ReportDto reportDto = new ReportDto();
        if (Optional.ofNullable(shop).isPresent()) {
            reportDto.setQuantity(shop.getItems().size());
            reportDto.setShopName(shop.getName());
            return reportDto;
        }

        return reportDto;
    }

    public Optional<Shop> find(String id) {
        return repository.findById(id);
    }

    public List<Shop> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Shop save(Shop shop) {
        return repository.save(shop);
    }

    public Shop update(String id, Shop shop) {

        final Optional<Shop> shopById = repository.findById(id);

        if(shopById.isPresent()) {
            Shop shopUpdate = shopById.get();
            shop.setName(shop.getName());
            return repository.save(shopUpdate);
        }

        return save(shop);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
