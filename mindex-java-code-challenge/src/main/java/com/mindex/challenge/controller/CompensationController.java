/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.controller;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is related to interacting with clients and responding to their api request
 *
 * This class is for Compensation type
 */

@RestController
public class CompensationController {

    @Autowired
    private CompensationService cs;

    /**
     * This function is for facilitating POST requests for Compensation type
     * @param   comp       this is the json representation which is converted to Compensation instance
     */

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation comp){
        // @RequestBody tells Spring MVC that the request payload is going to contain a json representation of the Compensation instance
        // so convert it to Compensation instance and then pass it to the function below
        // this is how POST request are mapped to the function
        return cs.create(comp);
    }

    /**
     * This function is for reading (GET requests) for Compensation type
     * @param    id      This reflects the employeeId corresponding to whcih we have search our compensation data
     * @return           A Compensation instance
     */

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id){
        return cs.read(id);
    }

}
