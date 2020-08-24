package com.pupov.test_nvision.controller;

import com.google.gson.Gson;
import com.pupov.test_nvision.model.Job;
import com.pupov.test_nvision.service.JobService;
import com.pupov.test_nvision.service.ParserXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ParserXML parserXML;

    private static final Gson gson = new Gson();

    @PostMapping(value = "/jobs", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postJobs(@RequestBody String requestBodyStr) {
        List<Job> jobList = parserXML.parseXml(requestBodyStr);
        jobService.saveAll(jobList);
        return new ResponseEntity(gson.toJson(getUserAmountSum(jobList)), HttpStatus.OK);
    }

    //формирование карты(map) для ответа на POST запрос
    private Map<String, Integer> getUserAmountSum(List<Job> jobList) {
        Map<String, Integer> map = new HashMap<>();
        for (Job job : jobList) {
            if (map.containsKey(job.getUser())) map.put(job.getUser(), map.get(job.getUser()) + job.getAmount());
            else map.put(job.getUser(), job.getAmount());
        }
        return map;
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getJobs(@RequestParam Map<String, String> requestParam) {
        //возвращаем все записи при отсутствии параметров
        if (requestParam.isEmpty()) return new ResponseEntity(gson.toJson(jobService.findAll()), HttpStatus.OK);
        //возвращаем отфильтрованные записи
        else return new ResponseEntity(gson.toJson(jobService.getFilteredList(requestParam)), HttpStatus.OK);
    }
}
