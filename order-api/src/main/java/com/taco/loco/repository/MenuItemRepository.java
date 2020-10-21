package com.taco.loco.repository;

import com.taco.loco.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
