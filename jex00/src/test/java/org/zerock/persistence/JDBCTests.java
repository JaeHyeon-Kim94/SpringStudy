package org.zerock.persistence;

import lombok.extern.log4j.Log4j2;

import static org.junit.Assert.fail;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class JDBCTests {
    static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        try(Connection con =
                    DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE"
                            , "book_ex"
                            , "book_ex"
                    )){
            log.info(con);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
