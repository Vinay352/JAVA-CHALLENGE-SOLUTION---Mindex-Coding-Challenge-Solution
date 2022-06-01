/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // this is where the business logic is implemented between the DAO and Controller layer
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository cr;

    /**
     * This function is to facilitate the POST requests for Compensation type
     * It creates a new Compensation entry in the database.
     *
     * @param    comp      The Compensation instance to save in the database
     * @return
     */
    @Override
    public Compensation create(Compensation comp) {
        return cr.save(comp);
    }

    /**
     * This function facilitates the GET requests for Compensation type
     *
     * @param   id      Employee Id whose compensation record is required
     * @return          An instance of Compensation
     */
    @Override
    public Compensation read(String id) {
        return cr.findByEmployeeId(id);
    }
}
