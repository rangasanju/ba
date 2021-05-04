package com.infyniteloop.ba.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infyniteloop.ba.model.Lobby;


@Repository
public interface LobbyRepository extends CrudRepository<Lobby, Long> {
	
	List<Lobby> findByDivision(String division);

}
