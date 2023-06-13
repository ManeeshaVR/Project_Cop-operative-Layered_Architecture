package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.MemberDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Member;
import lk.ijse.cooperative.entity.tm.AccountTM;
import lk.ijse.cooperative.entity.tm.MemberTM;

import java.sql.SQLException;
import java.util.List;

public interface NewMemberBo extends SuperBo {
    public boolean saveMember(MemberDTO member) throws SQLException;

    //public Account search(int memberNo) throws SQLException;

    public boolean updateMember(MemberDTO member) throws SQLException;

    public boolean deleteMember(String nic) throws SQLException;

    //public int generateNextMemberNo() throws SQLException;

    //public List<Integer> getMemberNos() throws SQLException;

    public ObservableList<MemberTM> getAllMembers() throws SQLException;

    public MemberDTO searchMember(String nic) throws SQLException;
}
