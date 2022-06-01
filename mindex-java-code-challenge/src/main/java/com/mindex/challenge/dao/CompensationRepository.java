/**
 *
 * @Author: Vinay Jain, MS in CS - RIT Student (2021 - 2023)
 *
 */

package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// reason behind declaring below class as an interface and not a class:-
// we plan on using very common operation (and not very specific or unique)
// and therefore Spring Framework already has facilities for those
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String>{
    Compensation findByEmployeeId(String id);
}
