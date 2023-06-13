package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.dto.SupplierDTO;
import lk.ijse.cooperative.dto.SuppliesDTO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.Supplies;
import lk.ijse.cooperative.entity.tm.SuppliesTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SuppliesBo extends SuperBo {

    public String generateNextOrderId() throws SQLException;

    public ObservableList<SuppliesTM> getAllSupplies() throws SQLException;

    public boolean updateSupplies(SuppliesDTO supplies) throws SQLException;

    public SuppliesDTO searchSupplies(String orderId) throws SQLException;

    public boolean saveAndUpdateSupplies(SuppliesDTO supplies) throws SQLException;

    public boolean deleteAndUpdateSupplies(String orderId, Integer qty, String itemId) throws SQLException;

    public List<String> getItemIds() throws SQLException;

    public List<String> getSupplierIds() throws SQLException;

    public ItemDTO searchItem(String itemId) throws SQLException;

    public SupplierDTO searchSupplier(String supplierId) throws SQLException;
}
