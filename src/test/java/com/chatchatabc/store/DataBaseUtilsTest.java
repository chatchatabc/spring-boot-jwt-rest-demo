package com.chatchatabc.store;

import com.github.database.rider.core.api.dataset.DataSet;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.FileOutputStream;

public class DataBaseUtilsTest extends BookStoreBaseTest {
    @Autowired
    private DataSource dataSource;

    /**
     * load all dataset into database for testing
     */
    @Test
    @DataSet({"db/datasets/operator.xml", "db/datasets/book.xml"})
    public void testLoadDataset() {
        Assertions.assertTrue(true);
    }

    /**
     * dump database schema to dtd file
     */
    @Test
    public void testDTDGeneration() throws Exception {
        DatabaseConnection databaseConnection = new MySqlConnection(dataSource.getConnection(), "book_store");
        final IDataSet dataSet = databaseConnection.createDataSet();
        FlatDtdDataSet.write(dataSet, new FileOutputStream("database.dtd"));
        FlatDtdDataSet.write(dataSet, new FileOutputStream("src/test/resources/db/datasets/database.dtd"));
    }
}
