/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // this is where the business logic is implemented between the DAO and Controller layer
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    private EmployeeService es;


    /**
     * This function is to read ( GET requests ) the employee id and calculate
     * the numberOfReports for that employee id
     *
     * @param     id      Employee id for which the directReport count is required
     * @return    ReportingStructure with employee details and the required count
     */
    @Override
    public ReportingStructure read(String id) {
        Employee temp = es.read(id);
        ReportingStructure rs = new ReportingStructure(temp, getTotalNumberOfDirectRrports(id));
        return rs;
    }

    /**
     * This function is to count DISTINCT entries in the directReports of the employee
     * with employee id "id". The idea is to treat this graphical representaion/heirarchy
     * as a tree and then perform modifed BFS on it.
     *
     * @param  id     Employee id for which the directReport count is required
     * @return        The required count
     */

    // a slight variation of BFS
    private int getTotalNumberOfDirectRrports(String id) {
        Employee temp = es.read(id);

        int count = 0;

        // if employee id is invalid
        if(temp == null){
            return 0;
        }

        Deque<Employee> deque = new ArrayDeque<Employee>();
        deque.addFirst(temp); // add root node

        Set<String> set = new HashSet<String>();

        set.add(temp.getEmployeeId()); // to keep track of distinct employee ids

        while(!deque.isEmpty()){
            temp = deque.pollFirst();
            String tempId = temp.getEmployeeId();

            // important to ensure distinct count
            if(!set.contains(tempId)){
                // if it is something that has been discovered earlier, no need to increase the count
                // else, increase the count
                count++;
                set.add(tempId);
            }

            // get all direct reports employees
            List<Employee> tempList = temp.getDirectReports();

            if(tempList != null){ // if there are direct reports employees
                for(int i = 0; i < tempList.size();i++){
                    // fetch the employee data from the employee ids found in direct reports
                    // add them to deque
                    deque.add(es.read(tempList.get(i).getEmployeeId()));
                }
            }
        }

        return count;
    }
}
