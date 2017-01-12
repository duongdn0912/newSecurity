package com.example.Repository;

import com.example.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dnduong on 1/12/2017.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
}
