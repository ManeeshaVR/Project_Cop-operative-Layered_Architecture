package lk.ijse.cooperative.dao.custom.impl;

import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl {
    public static String generateNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM managesupplies ORDER BY OrderId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    public static String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("O00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "O00"+id;
        }
        return "O001";
    }

}
