package com.chatchatabc.store;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class DBUnitConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DBUnitConfiguration.class);

    @Bean(initMethod = "run")
    public Runnable dataSetImporter(DataSource dataSource) {
        return () -> {
            try {
                DatabaseConnection databaseConnection = new MySqlConnection(dataSource.getConnection(), null);
                String[] DATASET_FILES = {"db/datasets/operator.xml", "db/datasets/book.xml"};
                for (String datasetFile : DATASET_FILES) {
                    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
                    final FlatXmlDataSet dataset = builder.build(this.getClass().getClassLoader().getResourceAsStream(datasetFile));
                    DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataset);
                }
            } catch (Exception e) {
                log.error("Failed to import dataset", e);
            }
        };
    }
}