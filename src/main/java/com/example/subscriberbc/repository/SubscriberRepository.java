package com.example.subscriberbc.repository;

import com.example.subscriberbc.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Long>, JpaSpecificationExecutor<Subscriber> {

    boolean existsByFirstNameAndLastNameAndMailAndPhone(String fName, String lName, String mail, String phone);

}
