package test.constant.asia.dao;

import java.util.List;


public interface AdminDao {
	
	public abstract void delete(Object object);
	public abstract void evict(Object object);
	public abstract Object load(long id, Class clz);
	public abstract Object merge(Object object);
	public abstract void save(Object object);
	public abstract void saveOrUpdate(Object object);
	public abstract void update(Object object);
	public abstract List getCityList();
	public abstract List getCompanyList();
	public abstract List getCompanyListByCity(long cityId);
	public abstract List getDepartmentList(long companyId);
	public abstract List getEmployeeList(long companyId, long officeId);
	public abstract List getEmployeeListByCity(long cityId);
	public abstract List getEmployeeListByDepartment(long departmentId);
	public abstract List getEmployeeDepartmentList(long employeeId);
	public abstract List getAssignDepartmentCount(long count);
	public abstract List getOfficeList(long companyId);
	public abstract List getOfficeListByCity(long cityId);


}