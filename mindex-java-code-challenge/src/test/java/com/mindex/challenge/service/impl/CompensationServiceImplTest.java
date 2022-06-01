/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort // for the port number assigned to the application at runtime
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before // execute the method below before executing anything else
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test // test cases
    public void test()
    {
        // Create checks
        Compensation testCompensation = new Compensation("62c1084e-6e34-4630-93fd-9153afb65309", "50", "May 31, 2022");
        // compensationService.create(testCompensation); // to add to database since this information is not present in the database when we run this program

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployeeId());
        assertCompensationEquivalence(testCompensation, createdCompensation);


//        Compensation createdCompensation =
//                restTemplate.exchange(compensationIdUrl,
//                        HttpMethod.PUT,
//                        new HttpEntity<Compensation>(testCompensation, headers),
//                        Compensation.class,
//                        testCompensation.getEmployeeId()).getBody();

        String s = createdCompensation.getEmployeeId();

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, s).getBody();

//        Compensation test =
//                restTemplate.exchange(compensationIdUrl,
//                        HttpMethod.GET,
//                        new HttpEntity<Compensation>(createdCompensation, headers),
//                        Compensation.class,
//                        createdCompensation.getEmployeeId()).getBody();

        assertNotNull(readCompensation);
        assertNotNull(readCompensation.getEmployeeId());
        // assertEquals(createdCompensation.getEmployeeId(), readCompensation.getEmployeeId()); // no need, this is being done in code line below
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    /**
     * The function below is a user defined assert function to compare 2 types of compensation.
     * @param   expected   Compensation type to compare
     * @param   actual     Compensation type to compare
     */
    private void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }

}