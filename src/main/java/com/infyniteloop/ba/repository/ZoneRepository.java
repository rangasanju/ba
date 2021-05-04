package com.infyniteloop.ba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.infyniteloop.ba.model.Zone;

@Repository
public interface ZoneRepository extends CrudRepository<Zone, Long> {

}
