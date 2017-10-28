package com.sen.streamlinetask.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sen.streamlinetask.entities.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

	public List<Users> findByUserNameAndPassword(String userName, String password);
}
