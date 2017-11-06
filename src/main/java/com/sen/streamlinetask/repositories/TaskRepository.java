package com.sen.streamlinetask.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sen.streamlinetask.entities.Tasks;

public interface TaskRepository extends CrudRepository<Tasks, Integer>{

	@Query("SELECT t FROM Tasks t WHERE t.userID=:userID AND DATE(t.scheduleDate)=DATE(SYSDATE())")
	public List<Tasks> getTodayTaskUsingUserID(@Param("userID") Integer userID);

}
