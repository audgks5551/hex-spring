package org.example.order.service.dataaccess.restaurant.adapter;

import lombok.RequiredArgsConstructor;
import org.example.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import org.example.order.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import org.example.order.service.dataaccess.restaurant.repository.RestaurantJpaRepository;
import org.example.order.service.domain.entity.Restaurant;
import org.example.order.service.domain.ports.output.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts = restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);

        Optional<List<RestaurantEntity>> restaurantEntities =
                restaurantJpaRepository.findByRestaurantIdAndProductIdIn(
                        restaurant.getId().getValue(), restaurantProducts);

        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
