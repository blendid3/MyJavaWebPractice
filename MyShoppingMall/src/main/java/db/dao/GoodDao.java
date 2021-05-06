package db.dao;

import db.domain.Good;

import java.sql.SQLException;
import java.util.List;

public interface GoodDao {
    List<Good> getGoodList() throws SQLException;
    Good getGoodByName(String goodName) throws SQLException;
}
