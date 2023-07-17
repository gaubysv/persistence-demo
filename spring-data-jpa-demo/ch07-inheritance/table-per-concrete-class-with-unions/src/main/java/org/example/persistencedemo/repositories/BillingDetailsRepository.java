package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
}
