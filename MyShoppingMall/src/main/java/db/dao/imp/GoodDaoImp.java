package db.dao.imp;

import db.core.JDBCTemplate;
import db.dao.GoodDao;
import db.domain.Good;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImp implements GoodDao {

    @Override
    public List<Good> getGoodList() throws SQLException {
        String sql = "select * from goods";
        ResultSet rs = JDBCTemplate.executeQuery(sql);
        List<Good> goodList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String NAME = rs.getString("NAME");
            int price = rs.getInt("price");
            String description = rs.getString("description");
            String brand = rs.getString("brand");
            String cpu_type = rs.getString("cpu_type");
            String cpu_brand = rs.getString("cpu_brand");
            String memory_capacity = rs.getString("memory_capacity");
            String hd_capacity = rs.getString("hd_capacity");
            String card_model = rs.getString("card_model");
            String displaysize = rs.getString("displaysize");
            String image = rs.getString("image");
            Good g1 = new Good(id, NAME, price, description, brand, cpu_brand, cpu_type, memory_capacity, hd_capacity, card_model, displaysize, image);
            goodList.add(g1);
        }
        return goodList;
    }

    @Override
    public Good getGoodByName(String goodName) throws SQLException {
        String sql = "select * from goods";
        ResultSet rs = JDBCTemplate.executeQuery(sql);
        List<Good> goodList = new ArrayList<>();
        while (rs.next()) {
            String NAME = rs.getString("NAME");
            if (!NAME.equals(goodName)) {
                continue;
            }
            int id = rs.getInt("id");
            int price = rs.getInt("price");
            String description = rs.getString("description");
            String brand = rs.getString("brand");
            String cpu_type = rs.getString("cpu_type");
            String cpu_brand = rs.getString("cpu_brand");
            String memory_capacity = rs.getString("memory_capacity");
            String hd_capacity = rs.getString("hd_capacity");
            String card_model = rs.getString("card_model");
            String displaysize = rs.getString("displaysize");
            String image = rs.getString("image");
            Good g1 = new Good(id, NAME, price, description, brand, cpu_brand, cpu_type, memory_capacity, hd_capacity, card_model, displaysize, image);
            return g1;
        }

        return null;
    }
}
