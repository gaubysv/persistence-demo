package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BillingDetailsRepository<T extends BillingDetails, ID> extends JpaRepository<T, ID> {

    List<T> findByOwner(String owner);
}
