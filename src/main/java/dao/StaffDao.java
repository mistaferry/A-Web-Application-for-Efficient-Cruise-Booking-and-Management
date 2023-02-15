package dao;

import exceptions.DbException;
import model.entity.Staff;

import java.util.List;

public interface StaffDao extends EntityDao<Staff> {
    List<Staff> getAllStaffByShipId(long id) throws DbException;
}
