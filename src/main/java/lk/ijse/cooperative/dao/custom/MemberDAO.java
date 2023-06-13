package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Member;
import lk.ijse.cooperative.entity.tm.MemberTM;

import java.sql.SQLException;

public interface MemberDAO extends CrudDAO<Member, MemberTM, String, String> {
    public int getCount() throws SQLException;

    public String getEmail(String text) throws SQLException;
}
