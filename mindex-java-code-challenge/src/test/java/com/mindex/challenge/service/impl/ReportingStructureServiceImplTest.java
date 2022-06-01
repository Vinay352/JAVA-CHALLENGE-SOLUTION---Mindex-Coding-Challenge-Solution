/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ReportingStructureServiceImplTest {

    // private String reportingStructureUrl;
    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private static EmployeeService es;

    @LocalServerPort // for the port number assigned to the application at runtime
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before // execute the method below before executing anything else
    public void setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test // test cases
    public void test() {

        Employee testEmployeeWithCount_4 = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f"); // employeee with directReports count = 4
        Employee testEmployeeWithCount_2 = employeeService.read("03aa1462-ffa9-4978-901b-7c001562cf6f"); // employeee with directReports count = 2
        Employee testEmployeeWithCount_0 = employeeService.read("b7839309-3348-463b-a7e3-5de1c168beb3"); // employeee with directReports count = 0

        // Read checks for directReportCount = 4
        ReportingStructure readEmployeeCount_4 = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, testEmployeeWithCount_4.getEmployeeId()).getBody();
        ReportingStructure testRS_4 = new ReportingStructure(testEmployeeWithCount_4, 4);

        assertNotNull(readEmployeeCount_4);
        assertReportingStructureEquivalence(testRS_4, readEmployeeCount_4);


        // Read checks for directReportCount = 2
        ReportingStructure readEmployeeCount_2 = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, testEmployeeWithCount_2.getEmployeeId()).getBody();
        ReportingStructure testRS_2 = new ReportingStructure(testEmployeeWithCount_2, 2);

        assertNotNull(readEmployeeCount_2);
        assertReportingStructureEquivalence(testRS_2, readEmployeeCount_2);



        // Read checks for directReportCount = 0
        ReportingStructure readEmployeeCount_0 = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, testEmployeeWithCount_0.getEmployeeId()).getBody();
        ReportingStructure testRS_0 = new ReportingStructure(testEmployeeWithCount_0, 0);

        assertNotNull(readEmployeeCount_0);
        assertReportingStructureEquivalence(testRS_0, readEmployeeCount_0);

    }


    /**
     * The function below is a user defined assert function to compare 2 types of ReportingStructure.
     * @param   expected   ReportingStructure type to compare
     * @param   actual     ReportingStructure type to compare
     */
    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());

        assertEquals( expected.getNumberOfReports(), actual.getNumberOfReports() );
    }


}
