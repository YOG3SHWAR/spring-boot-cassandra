package com.sapient.cassandra.repository;

import com.sapient.cassandra.domain.Account;
import com.sapient.cassandra.domain.AccountKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCrudRepository extends CrudRepository<Account, AccountKey> {

    Account findFirstByKeySortCodeAndKeyAccountNumber(String sortCode, String accountNumber);

    List<Account> findAllByKeySortCodeAndKeyAccountNumber(List<String> sortCode, List<String> accountNumber);

}
