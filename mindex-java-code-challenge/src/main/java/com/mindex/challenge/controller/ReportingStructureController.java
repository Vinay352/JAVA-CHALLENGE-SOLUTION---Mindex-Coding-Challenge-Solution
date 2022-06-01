/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * This class is related to interacting with clients and responding to their api request
 *
 * This class is for ReportingStructure type
 */

@RestController // this annotation is from spring MVC.
// web layer in spring boot application leverages a part of spring framework leverages what is called spring MVC
// it lets you build server side code which maps to urls and provides responses
// converts to JSON or XML format
// A class being annotated as RestController means it has methods that map to URL request
public class ReportingStructureController {

    @Autowired
    // this lets the spring MVC know that this class has a dependency on a service which needs to be injected when working with this class
    // it automatically creates an instance of the service class, otherwise we would have to create an instance of that just like we do in java
    private ReportingStructureService rss;

    /**
     * This function is for reading (GET requests) for ReportingStructure type.
     * It returns the employee data and number of DISTINCT people who directly report to the employee
     * @param id
     * @return
     */

    @GetMapping("/reportingStructure/{id}") // mapping to get reporting structure of an employee
    public ReportingStructure read(@PathVariable String id){ // PathVariable is to map the "id" in HTTP request form to the one being passed as argument in this function
//        Employee temp = es.read(id);
//        ReportingStructure rs = new ReportingStructure(temp, getTotalNumberOfDirectRrports(id));
//        return rs;

        return rss.read(id);
    }

}
