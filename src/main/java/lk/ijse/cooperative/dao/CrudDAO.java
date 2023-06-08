package lk.ijse.cooperative.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dto.Account;
import lk.ijse.cooperative.dto.tm.AccountTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T, TM, ID, ID2> extends SuperDAO{
    public boolean save(T dto) throws SQLException;

    public boolean update(T dto) throws SQLException;

    public boolean delete(ID id) throws SQLException;

    public T search(ID id) throws SQLException;

    public ID generateNextId() throws SQLException;

    public String splitId(String currentId);

    public List<ID> getIds() throws SQLException;

    public ObservableList<TM> getAll() throws SQLException;

    public T search2(ID2 id2) throws SQLException;

}
