package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.MemberDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Member;
import lk.ijse.cooperative.entity.tm.AccountTM;

import java.sql.SQLException;
import java.util.List;

public interface NewAccountBo extends SuperBo {
    public boolean saveAccount(AccountDTO account) throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

    public boolean updateAccount(AccountDTO account) throws SQLException;

    public boolean deleteAccount(Integer memberNo) throws SQLException;

    public Integer generateNextMemberNo() throws SQLException;

    public List<String> getNics() throws SQLException;

    public ObservableList<AccountTM> getAllAccounts() throws SQLException;

    public AccountDTO search2(String nic) throws SQLException;

    public MemberDTO searchMember(String nic) throws SQLException;
}
