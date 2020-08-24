package com.pupov.test_nvision.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pupov.test_nvision.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class ParserXML {
    private static final Logger logger = LoggerFactory.getLogger(ParserXML.class);

    private XmlMapper xmlMapper = new XmlMapper();

    public List<Job> parseXml(String xmlStr) {
        try {
            return xmlMapper.readValue(xmlStr, new TypeReference<List<Job>>() {});
        } catch (JsonProcessingException e) {
            logger.error("Error parse xml", e);
        }
        return emptyList();
    }
}
