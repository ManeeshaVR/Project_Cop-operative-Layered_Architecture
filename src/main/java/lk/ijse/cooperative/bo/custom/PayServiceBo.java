package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.PayServiceDTO;
import lk.ijse.cooperative.dto.ServiceDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.PayService;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.tm.PaySerTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PayServiceBo extends SuperBo {
    public boolean paidService(PayServiceDTO payService) throws SQLException;

    public PayServiceDTO searchPayService(String payId) throws SQLException;

    public boolean updatePayService(PayServiceDTO payService) throws SQLException;

    public boolean deletePayService(String payId, String serId, boolean b) throws SQLException;

    public String generateNextPayServiceId() throws SQLException;

    public ObservableList<String> getServiceIds() throws SQLException;

    public ObservableList<PaySerTM> getAllPayServices() throws SQLException;

    public ServiceDTO searchService(String id) throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

    public Double getServiceInterest() throws SQLException;
}
