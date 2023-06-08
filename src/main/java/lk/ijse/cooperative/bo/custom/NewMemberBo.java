package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.Account;
import lk.ijse.cooperative.dto.tm.AccountTM;

import java.sql.SQLException;
import java.util.List;

public interface NewMemberBo extends SuperBo {
    public boolean save(Account account) throws SQLException;

    //public Account search(int memberNo) throws SQLException;

    public boolean update(Account account) throws SQLException;

    public boolean delete(int memberNo) throws SQLException;

    public int generateNextMemberNo() throws SQLException;

    public List<Integer> getMemberNos() throws SQLException;

    public ObservableList<AccountTM> getAll() throws SQLException;

    public Account search(String nic) throws SQLException;
}
