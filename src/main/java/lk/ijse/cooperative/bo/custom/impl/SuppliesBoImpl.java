package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.SuppliesBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dao.custom.SupplierDAO;
import lk.ijse.cooperative.dao.custom.SuppliesDAO;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.dto.SupplierDTO;
import lk.ijse.cooperative.dto.SuppliesDTO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.Supplies;
import lk.ijse.cooperative.entity.tm.SuppliesTM;

import java.sql.SQLException;
import java.util.List;

public class SuppliesBoImpl implements SuppliesBo {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIES);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public String generateNextOrderId() throws SQLException {
        return suppliesDAO.generateNextId();
    }

    @Override
    public ObservableList<SuppliesTM> getAllSupplies() throws SQLException {
        return suppliesDAO.getAll();
    }

    @Override
    public boolean updateSupplies(SuppliesDTO supplies) throws SQLException {
        return suppliesDAO.update(new Supplies(supplies.getOrderId(), supplies.getSupId(), supplies.getSupName(), supplies.getItemId(), supplies.getItemName(), supplies.getQty(), supplies.getUniPrice(), supplies.getAmount(), supplies.getDate()));
    }

    @Override
    public SuppliesDTO searchSupplies(String orderId) throws SQLException {
        Supplies supplies = suppliesDAO.search(orderId);
        return new SuppliesDTO(supplies.getOrderId(), supplies.getSupId(), supplies.getSupName(), supplies.getItemId(), supplies.getItemName(), supplies.getQty(), supplies.getUniPrice(), supplies.getAmount(), supplies.getDate());
    }

    @Override
    public boolean saveAndUpdateSupplies(SuppliesDTO supplies) throws SQLException {
        return suppliesDAO.saveAndUpdate(new Supplies(supplies.getOrderId(), supplies.getSupId(), supplies.getSupName(), supplies.getItemId(), supplies.getItemName(), supplies.getQty(), supplies.getUniPrice(), supplies.getAmount(), supplies.getDate()));
    }

    @Override
    public boolean deleteAndUpdateSupplies(String orderId, Integer qty, String itemId) throws SQLException {
        return suppliesDAO.deleteAndUpdate(orderId, qty, itemId);
    }

    @Override
    public List<String> getItemIds() throws SQLException {
        return itemDAO.getIds();
    }

    @Override
    public List<String> getSupplierIds() throws SQLException {
        return supplierDAO.getIds();
    }

    @Override
    public ItemDTO searchItem(String itemId) throws SQLException {
        Item item = itemDAO.search(itemId);
        return new ItemDTO(item.getItemId(), item.getName(), item.getType(), item.getUnitPrice(), item.getDescription(), item.getQty());
    }

    @Override
    public SupplierDTO searchSupplier(String supplierId) throws SQLException {
        Supplier supplier = supplierDAO.search(supplierId);
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getContact(), supplier.getAddress());
    }
}
