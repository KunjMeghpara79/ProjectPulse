package com.example.ProjectPulse.Task;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {

    @Transactional
    @Modifying
    @Query("Delete from Task t where t.employeeId = :employeeId")
    void deleteTasks(@Param("employeeId") int employeeId);
}
