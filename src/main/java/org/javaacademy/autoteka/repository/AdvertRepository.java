package org.javaacademy.autoteka.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.javaacademy.autoteka.entity.Advert;
import org.springframework.stereotype.Component;

@Component
public class AdvertRepository {
    private final Map<String, Advert> adverts = new HashMap<>();

    /**
     * Add advertisement in repo
     *
     * @param advert new advertisement
     */
    public Advert add(@NonNull Advert advert) {
        advert.setUuid(UUID.randomUUID().toString());
        return adverts.put(advert.getUuid(), advert);
    }

    /**
     * Get all advertisements
     */
    public List<Advert> findAll() {
        return new ArrayList<>(adverts.values());
    }

    /**
     * Find Advertisement by key
     *
     * @param id unique number
     * @return Optional result find
     */
    public Optional<Advert> findById(@NonNull String id) {
        return Optional.ofNullable(adverts.get(id));
    }

    /**
     * Find Advertisement by brand
     *
     * @param brand brand
     * @return List
     */
    public List<Advert> findByBrand(@NonNull String brand) {
        return adverts.values().stream().filter(e -> e.getBrand().equals(brand)).toList();
    }

    /**
     * Find Advertisement by color
     *
     * @param color color
     * @return List
     */
    public List<Advert> findByColor(@NonNull String color) {
        return adverts.values().stream().filter(e -> e.getColor().equals(color)).toList();
    }

    /**
     * Find Advertisement by price
     *
     * @param price price
     * @return List
     */
    public List<Advert> findByPrice(@NonNull BigDecimal price) {
        return adverts.values().stream().filter(e -> e.getPrice().compareTo(price) == 0).toList();
    }

    /**
     * Find Advertisement by model
     *
     * @param model price
     * @return List
     */
    public List<Advert> findByModel(@NonNull String model) {
        return adverts.values().stream().filter(e -> e.getModel().equals(model)).toList();
    }

    /**
     * Find all Adverts by date
     *
     * @param date date for search
     * @return List
     */
    public List<Advert> findByDate(@NonNull LocalDate date) {
        return adverts.values().stream().filter(e -> e.getPostingDate().isEqual(date)).toList();
    }

    /**
     * Delete Advertisement by key
     *
     * @param id unique number
     * @return result deleting
     */
    public boolean deleteById(@NonNull String id) {
        return adverts.remove(id) != null;
    }

    /**
     * Delete all Advertisement from repo
     *
     */
    public boolean deleteAll() {
        adverts.clear();
        return adverts.isEmpty();
    }
}
