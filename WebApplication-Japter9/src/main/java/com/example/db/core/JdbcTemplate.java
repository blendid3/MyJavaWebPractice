package com.example.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public static void query(PreparedStatementCreator pc, RowCallbackHandler rc)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelp.getConnect();
            ps = pc.createPreparedStatement(conn);
            rs = ps.executeQuery();
            while (rs.next()) {
                rc.processRow(rs);
            }

        } catch (SQLException e){
            throw new DataAccessException("SQLException in JdbcTemplate query", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("ClassNotFoundException in JdbcTemplate query", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new DataAccessException("conn close errors in JdbcTemplate query", e);
                }

            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DataAccessException("preparedStatement close errors in JdbcTemplate query", e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException("ResultSet close errors in JdbcTemplate query", e);
                }
            }
        }


    }
    public static void update(PreparedStatementCreator pc) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBHelp.getConnect();
            ps = pc.createPreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("SQLException in JdbcTemplate update", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("ClassNotFoundException in JdbcTemplate update", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new DataAccessException("conn close errors in JdbcTemplate update", e);
                }

            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DataAccessException("preparedStatement close errors in JdbcTemplate update", e);
                }
            }

        }
    }
}
