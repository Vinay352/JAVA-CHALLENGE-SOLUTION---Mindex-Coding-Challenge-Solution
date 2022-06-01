/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // for getters and setters
@AllArgsConstructor // for a constructor with all arguments : require lombok 1.18.22 and not 1.18.20
// @NoArgsConstructor // constructor with no arguments
public class ReportingStructure {

    private Employee employee;
    private int numberOfReports;

}
