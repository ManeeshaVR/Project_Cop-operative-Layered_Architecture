package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dto.Item;
import lk.ijse.cooperative.dto.tm.ItemTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, ItemTM, String, String> {
    public int getCount() throws SQLException;

    public Integer getTypes(String type) throws SQLException;

    public boolean updateQty1(Integer qty, String itemId) throws SQLException;

    public boolean updateQty2(Integer qty, String itemId) throws SQLException;

    public int getQtyCount() throws SQLException;
}
