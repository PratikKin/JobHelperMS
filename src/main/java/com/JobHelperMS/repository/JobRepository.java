package com.JobHelperMS.repository;

import com.JobHelperMS.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    @Query("SELECT j.id FROM Job j ORDER BY j.id DESC LIMIT 1")
    String findLastJobId();
}

