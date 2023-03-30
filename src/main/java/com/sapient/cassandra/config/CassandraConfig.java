package com.sapient.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String cassandraKeyspace;

    @Override
    protected String getKeyspaceName() {
        return cassandraKeyspace;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(cassandraKeyspace)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    @Override
    protected List<String> getStartupScripts() {
        return Collections.singletonList("CREATE TABLE IF NOT EXISTS " + cassandraKeyspace + ".test(id UUID PRIMARY KEY, greeting text, occurrence timestamp) WITH default_time_to_live = 600;");
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.sapient"};
    }
}
