package org.example.order.service.dataaccess.restaurant.mapper;

import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.ProductId;
import org.example.domain.valueobject.RestaurantId;
import org.example.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import org.example.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import org.example.order.service.domain.entity.Product;
import org.example.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> products = restaurantEntities.stream().map(entity -> new Product(
                        new ProductId(entity.getProductId()),
                        entity.getProductName(),
                        new Money(entity.getProductPrice())
                ))
                .toList();

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(products)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }
}
