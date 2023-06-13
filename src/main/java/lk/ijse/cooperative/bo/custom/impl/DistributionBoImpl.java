package lk.ijse.cooperative.bo.custom.impl;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.*;
import lk.ijse.cooperative.dto.DistributeDTO;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.entity.Distribute;
import lk.ijse.cooperative.entity.Item;

import java.sql.SQLException;
import java.util.List;

public class DistributionBoImpl implements DistributionBo {

    DistributeDAO distributeDAO = (DistributeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DISTRIBUTE);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIES);

    @Override
    public String generateDistributionNextId() throws SQLException {
        return distributeDAO.generateNextId();
    }

    @Override
    public boolean saveAndUpdateDistribution(DistributeDTO distribute) throws SQLException {
        return distributeDAO.saveAndUpdate(new Distribute(distribute.getDisId(), distribute.getItemId(), distribute.getItemName(), distribute.getDate(), distribute.getDep(), distribute.getDisQty(), distribute.getDesc()));
    }

    @Override
    public boolean deleteAndUpdateDistribution(String disId, String itemId, int disQty) throws SQLException {
        return distributeDAO.deleteAndUpdate(disId, itemId, disQty);
    }

    @Override
    public DistributeDTO searchDistribution(String disId) throws SQLException {
        Distribute distribute = distributeDAO.search(disId);
        return new DistributeDTO(distribute.getDisId(), distribute.getItemId(), distribute.getItemName(), distribute.getDate(), distribute.getDep(), distribute.getDisQty(), distribute.getDesc());
    }

    @Override
    public ObservableList<Distribute> getAllDistribution() throws SQLException {
        return distributeDAO.getAll();
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemId(), item.getName(), item.getType(), item.getUnitPrice(), item.getDescription(), item.getQty());
    }

    @Override
    public List<String> getItemIds() throws SQLException {
        return suppliesDAO.getIds();
    }
}
