package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.PayService;
import lk.ijse.cooperative.entity.tm.PaySerTM;

import java.sql.SQLException;

public interface PaySerDAO extends CrudDAO<PayService, PaySerTM, String, String> {
    public boolean paid(PayService payService) throws SQLException;

    public boolean deletePay(String payId, String serId, boolean b) throws SQLException;
}
