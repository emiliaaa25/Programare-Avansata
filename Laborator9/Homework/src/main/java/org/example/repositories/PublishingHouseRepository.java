package org.example.repositories;

import org.example.entities.PublishingHouse;

public class PublishingHouseRepository extends AbstractRepository<PublishingHouse> {
    public PublishingHouseRepository(Class<PublishingHouse> entityClass) {
        super(entityClass);
    }
}
