package dao;

import exceptions.DbException;
import model.Staff;

import java.util.List;

public interface StaffDao extends EntityDao<Staff> {
    List<Staff> getAllStaffByShipId(long id) throws DbException;
}
