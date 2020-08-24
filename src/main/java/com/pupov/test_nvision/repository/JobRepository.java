package com.pupov.test_nvision.repository;

import com.pupov.test_nvision.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    //выборка всех записей, упорядоченных по дате
    List<Job> findAllByOrderByTimeAsc();
}
