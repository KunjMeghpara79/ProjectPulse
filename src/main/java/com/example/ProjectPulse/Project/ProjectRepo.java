package com.example.ProjectPulse.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {
    public boolean existsByProjectName(String projectName);
}
