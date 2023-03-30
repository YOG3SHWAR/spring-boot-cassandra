package com.sapient.cassandra.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("account")
@Data
@Builder
public class Account {

    @PrimaryKey
    private AccountKey key;

    @Column
    private String data;

}
