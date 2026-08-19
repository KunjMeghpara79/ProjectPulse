package com.example.ProjectPulse.Repositories;

import com.example.ProjectPulse.Models.Entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {

    @Transactional //If the query fails halfway through, the database rolls back to its original state.
    @Modifying /* This instructs Spring Data JPA that the execution will change the database state
     via an UPDATE, INSERT, or DELETE command instead of a SELECT statement.*/
    @Query("Delete from Task t where t.employeeId = :employeeId")
    void deleteTasks(@Param("employeeId") int employeeId);

    /*
    this method will be executed when the employee will be deleted
    so their tasks will also be deleted so we have to go through all projects in which the particular
    employee exists and from that project object delete the tasks which contains that employeeId.
    by using this query method we can directly delete the tasks from tasks table
     */
}
