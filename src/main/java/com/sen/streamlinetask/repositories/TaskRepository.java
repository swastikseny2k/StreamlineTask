package com.sen.streamlinetask.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sen.streamlinetask.entities.Tasks;

public interface TaskRepository extends CrudRepository<Tasks, Integer>{

}
