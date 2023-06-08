package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dto.Account;
import lk.ijse.cooperative.dto.tm.AccountTM;

import java.sql.SQLException;

public interface AccountDAO extends CrudDAO<Account, AccountTM, Integer, String> {
    public String getEmail(int text) throws SQLException;
}
