package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dao.custom.MemberDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.MemberDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Member;
import lk.ijse.cooperative.entity.tm.AccountTM;

import java.sql.SQLException;
import java.util.List;

public class NewAccountBoImpl implements NewAccountBo{

    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    @Override
    public boolean saveAccount(AccountDTO account) throws SQLException {
        return accountDAO.save(new Account(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail()));
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }

    @Override
    public boolean updateAccount(AccountDTO account) throws SQLException {
        return accountDAO.update(new Account(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail()));
    }

    @Override
    public boolean deleteAccount(Integer memberNo) throws SQLException {
        return accountDAO.delete(memberNo);
    }

    @Override
    public Integer generateNextMemberNo() throws SQLException {
        return accountDAO.generateNextId();
    }

    @Override
    public List<String> getNics() throws SQLException {
        return memberDAO.getIds();
    }

    @Override
    public ObservableList<AccountTM> getAllAccounts() throws SQLException {
        return accountDAO.getAll();
    }

    @Override
    public AccountDTO search2(String nic) throws SQLException {
        Account account = accountDAO.search2(nic);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }

    @Override
    public MemberDTO searchMember(String nic) throws SQLException {
        Member member = memberDAO.search2(nic);
        return new MemberDTO(member.getNic(), member.getName(), member.getAge(), member.getPosition(), member.getDepartment(), member.getSalary(), member.getJoinDate());
    }
}
