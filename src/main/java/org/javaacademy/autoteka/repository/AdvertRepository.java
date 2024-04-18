package org.javaacademy.autoteka.repository;

import lombok.NonNull;
import org.javaacademy.autoteka.entity.Advert;
import org.springframework.stereotype.Component;

import java.util.*;

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
     * @param key unique number
     * @return Optional<Advertisement> result find
     */
    public Optional<Advert> findByKey(@NonNull String key) {
        return Optional.ofNullable(adverts.get(key));
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

    /**
     * Return total count records in repo
     *
     */
    public int getTotalCount() {
        return adverts.size();
    }
}
