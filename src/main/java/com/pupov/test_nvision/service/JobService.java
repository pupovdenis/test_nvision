package com.pupov.test_nvision.service;

import com.pupov.test_nvision.model.Job;

import java.util.List;
import java.util.Map;

public interface JobService {
    List<Job> findAll();

    void saveAll(List<Job> jobList);

    List<Job> getFilteredList(Map<String, String> requestParams);
}
