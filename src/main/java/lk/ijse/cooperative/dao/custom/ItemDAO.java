package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.tm.ItemTM;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, ItemTM, String, String> {
    public int getCount() throws SQLException;

    public Integer getTypes(String type) throws SQLException;

    public boolean updateQty1(Integer qty, String itemId) throws SQLException;

    public boolean updateQty2(Integer qty, String itemId) throws SQLException;

    public int getQtyCount() throws SQLException;
}
