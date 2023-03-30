package com.sapient.cassandra.repository;

import com.sapient.cassandra.domain.Account;
import com.sapient.cassandra.domain.AccountKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CassandraRepository<Account, AccountKey> {

    @Value("${spring.data.cassandra.keyspace-name}")
    String keyspace = "keysapce";

    Account findFirstByKeySortCodeAndKeyAccountNumber(String sortCode, String accountNumber);

    @Query("SELECT * FROM " + keyspace + ".account WHERE sortcode IN ?0 AND accountnumber IN ?1")
    List<Account> findAllByKeySortCodeAndKeyAccountNumber(List<String> sortCode, List<String> accountNumber);

    //    List<Account> findAllByKeySortCode(List<String> sortCodes);
    @Query("SELECT * FROM account WHERE sortcode IN ?0")
//    SELECT * FROM example.account WHERE name='name1' AND sortcode IN ('123','456') AND accountnumber IN ('1','2')
    List<Account> findAllByKeySortCode(List<AccountKey> accountKeys);
}
