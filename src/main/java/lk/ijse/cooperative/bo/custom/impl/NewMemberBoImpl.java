package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.MemberDAO;
import lk.ijse.cooperative.dto.MemberDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Member;
import lk.ijse.cooperative.entity.tm.AccountTM;
import lk.ijse.cooperative.entity.tm.MemberTM;

import java.sql.SQLException;
import java.util.List;

public class NewMemberBoImpl implements NewMemberBo{

    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);


    @Override
    public boolean saveMember(MemberDTO member) throws SQLException {
        return memberDAO.save(new Member(member.getNic(), member.getName(), member.getAge(), member.getPosition(), member.getDepartment(), member.getSalary(), member.getJoinDate()));
    }

    @Override
    public boolean updateMember(MemberDTO member) throws SQLException {
        return memberDAO.update(new Member(member.getNic(), member.getName(), member.getAge(), member.getPosition(), member.getDepartment(), member.getSalary(), member.getJoinDate()));
    }

    @Override
    public boolean deleteMember(String nic) throws SQLException {
        return memberDAO.delete(nic);
    }

    @Override
    public ObservableList<MemberTM> getAllMembers() throws SQLException {
        return memberDAO.getAll();
    }

    @Override
    public MemberDTO searchMember(String nic) throws SQLException {
        Member member = memberDAO.search2(nic);
        return new MemberDTO(member.getNic(), member.getName(), member.getAge(), member.getPosition(), member.getDepartment(), member.getSalary(), member.getJoinDate());
    }
}
