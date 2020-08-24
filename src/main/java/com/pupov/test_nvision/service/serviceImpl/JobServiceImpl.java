package com.pupov.test_nvision.service.serviceImpl;

import com.pupov.test_nvision.model.Job;
import com.pupov.test_nvision.repository.JobRepository;
import com.pupov.test_nvision.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.pupov.test_nvision.utils.DateUtil.getDateOfString;
import static com.pupov.test_nvision.utils.DateUtil.getStringOfDate;

@Transactional
@Repository
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAllByOrderByTimeAsc();
    }

    @Override
    public void saveAll(List<Job> jobList) {
        jobRepository.saveAll(jobList);
    }

    //получение списка записей отфильтрованного по параметрам запроса
    @Override
    public List<Job> getFilteredList(Map<String, String> requestParams) {
        List<Job> jobList = jobRepository.findAll();

        //докладываем параметры для установки правильного временного диапазона
        if (!requestParams.containsKey("dateFrom")) requestParams.put("dateFrom", getStringOfDate(new Date(0L))); //от самой ранней записи
        if (!requestParams.containsKey("dateTo")) requestParams.put("dateTo", getStringOfDate(new Date())); //до самой поздней

        //фильтруем лист
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                switch (entry.getKey()) {
                    case "user" :
                        jobList.removeIf(element -> !element.getUser().equals(entry.getValue()));
                        break;
                    case "type" :
                        jobList.removeIf(element -> !element.getType().equals(entry.getValue()));
                        break;
                    case "device" :
                        jobList.removeIf(element -> !element.getDevice().equals(entry.getValue()));
                        break;
                    case "timeFrom" :
                        if (!entry.getValue().isEmpty())
                            jobList.removeIf(element -> element.getTime().before(getDateOfString(entry.getValue())));
                        break;
                    case "timeTo" :
                        if (!entry.getValue().isEmpty())
                            jobList.removeIf(element -> element.getTime().after(getDateOfString(entry.getValue())));
                        break;
                }
        }
        return jobList;
    }
}
