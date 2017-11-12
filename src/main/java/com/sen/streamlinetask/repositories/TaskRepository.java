package com.sen.streamlinetask.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sen.streamlinetask.entities.Tasks;

@Transactional
public interface TaskRepository extends CrudRepository<Tasks, Integer>{

	@Query("SELECT t FROM Tasks t WHERE t.userID=:userID AND DATE(t.scheduleDate)<=DATE(SYSDATE()) AND t.taskStatus=1")
	public List<Tasks> getTodayTaskUsingUserID(@Param("userID") Integer userID);

	@Modifying
	@Query("UPDATE Tasks SET taskStatus=:taskStatus WHERE taskID=:taskID")
	public void updateTaskStatus(@Param("taskStatus") Integer taskStatus, @Param("taskID") Integer taskID);

	@Query("SELECT t FROM Tasks t WHERE t.taskDesc LIKE %:desc% AND t.userID=:userID")
	public List<Tasks> findTaskByDescription(@Param("desc") String desc, @Param("userID") Integer userID);
 
}
