package com.App.repository;

import com.App.Entity.Todo;
import com.App.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface todoRepository extends JpaRepository<Todo,Integer> {
  Page<Todo> findByUser(User user, Pageable pageable);
 @Query(value = "SELECT * FROM todo WHERE created_at >=? AND created_at <=? AND user_id =?",nativeQuery = true)
 List<Todo> findByCreatedAtBetween( String to, String from,@Param("user_id") int id);
 @Query(value = "SELECT * FROM todo WHERE  priority =1 AND  user_id =?",nativeQuery = true)
 List<Todo> findByPriority(@Param("user_id") int id);

}
