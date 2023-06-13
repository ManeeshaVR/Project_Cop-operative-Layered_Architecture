package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.tm.ItemTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBo extends SuperBo {

    public boolean saveItem(ItemDTO item) throws SQLException;

    public boolean updateItem(ItemDTO item) throws SQLException;

    public boolean deleteItem(String id) throws SQLException;

    public ItemDTO searchItem(String id) throws SQLException;

    public ObservableList<ItemTM> getAllItems() throws SQLException;

    public String generateNextItemId() throws SQLException;
}
