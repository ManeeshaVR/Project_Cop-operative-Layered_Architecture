package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.InterestDAO;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.tm.ItemTM;

import java.sql.SQLException;

public class ItemBoImpl implements ItemBo{

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO item) throws SQLException {
        return itemDAO.save(new Item(item.getItemId(), item.getName(), item.getType(), item.getUnitPrice(), item.getDescription(), item.getQty()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException {
        return itemDAO.update(new Item(item.getItemId(), item.getName(), item.getType(), item.getUnitPrice(), item.getDescription(), item.getQty()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemId(), item.getName(), item.getType(), item.getUnitPrice(), item.getDescription(), item.getQty());
    }

    @Override
    public ObservableList<ItemTM> getAllItems() throws SQLException {
        return itemDAO.getAll();
    }

    @Override
    public String generateNextItemId() throws SQLException {
        return itemDAO.generateNextId();
    }
}
