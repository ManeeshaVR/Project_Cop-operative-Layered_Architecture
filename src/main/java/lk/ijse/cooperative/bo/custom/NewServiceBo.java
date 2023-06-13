package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.ServiceDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.tm.OtherSerTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface NewServiceBo extends SuperBo {
    public ObservableList<OtherSerTM> getAllService() throws SQLException;

    public String generateNextServiceId() throws SQLException;

    public boolean saveService(ServiceDTO service) throws SQLException;

    public boolean updateService(ServiceDTO service) throws SQLException;

    public boolean deleteService(String id) throws SQLException;

    public ServiceDTO searchService(String id) throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

    public List<Integer> getMemberNos() throws SQLException;
}
