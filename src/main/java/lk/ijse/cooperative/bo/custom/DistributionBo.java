package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dao.custom.impl.ItemDAOImpl;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.DistributeDTO;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.entity.Distribute;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DistributionBo extends SuperBo {

    public String generateDistributionNextId() throws SQLException;

    public boolean saveAndUpdateDistribution(DistributeDTO distribute) throws SQLException;

    public boolean deleteAndUpdateDistribution(String disId, String itemId, int disQty) throws SQLException;

    public DistributeDTO searchDistribution(String disId) throws SQLException;

    public ObservableList<Distribute> getAllDistribution() throws SQLException;

    public ItemDTO searchItem(String id) throws SQLException;

    public List<String> getItemIds() throws SQLException;
}
