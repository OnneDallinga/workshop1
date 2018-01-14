package com.rsvier.workshop1.database;

import java.sql.*;
import com.zaxxer.hikari.*;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
        config.setJdbcUrl( "jdbc:mysql://localhost/rsvier" );
        config.setUsername( "Onne" );
        config.setPassword( "Once!UponAT1me" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }
 
    private DataSource() {}
 
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

