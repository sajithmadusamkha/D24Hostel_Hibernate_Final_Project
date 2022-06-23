package lk.D24_Hostel.hostelSystem.dao;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{
    boolean add(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;

    T find(ID id) throws Exception;

    List<T> findAll() throws Exception;
}
