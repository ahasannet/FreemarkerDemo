package test.constant.asia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.constant.asia.db.City;
import test.constant.asia.db.Company;
import test.constant.asia.db.Department;
import test.constant.asia.db.Employee;
import test.constant.asia.db.EmployeeDepartment;
import test.constant.asia.db.Office;
import test.constant.asia.db.ParentModel;


@Service("adminDao")
@Transactional
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	
	private Object trackVersion(Object object) {
		if(object instanceof ParentModel) {
			if(((ParentModel)object).getId() == 0)
				((ParentModel)object).setCreated(new Date());
			else
				((ParentModel)object).setUpdated(new Date());
		}
		return object;
	}
	
	public void delete(Object object) {
		if(object instanceof City) {
			getHibernateTemplate().bulkUpdate("Update Company SET city = null WHERE city.id = ?", ((City)object).getId());
			getHibernateTemplate().bulkUpdate("Update Office SET city = null WHERE city.id = ?", ((City)object).getId());
			getHibernateTemplate().bulkUpdate("Update Employee SET city = null WHERE city.id = ?", ((City)object).getId());
		}
		else if(object instanceof Company) {
			getHibernateTemplate().bulkUpdate("delete from Department WHERE company.id = ?", ((Company)object).getId());
			deleteOffice(((Company)object).getId());
		}
		else if(object instanceof Department)
			getHibernateTemplate().bulkUpdate("delete from EmployeeDepartment WHERE department.id = ?", ((Department)object).getId());
		else if(object instanceof Employee)
			deleteEmployeeDepartment(((Employee)object).getId());
		else if(object instanceof Office)
			deleteEmployee(((Office)object).getId());
		getHibernateTemplate().delete(object);
	}
	
	private void deleteOffice(long companyId) {
		List<Office> officeList = getHibernateTemplate().find("from Office where company.id = ?", companyId);
		for(Office office : officeList) {
			deleteEmployee(office.getId());
			getHibernateTemplate().delete(office);
		}
	}
	
	private void deleteEmployee(long officeId) {
		List<Employee> employeeList = getHibernateTemplate().find("from Employee where office.id = ?", officeId);
		for(Employee employee : employeeList) {
			deleteEmployeeDepartment(employee.getId());
			getHibernateTemplate().delete(employee);
		}
	}
	
	private void deleteEmployeeDepartment(long employeeId) {
		List<EmployeeDepartment> empDeptList = getHibernateTemplate().find("from EmployeeDepartment " +
				"where employee.id = ?", employeeId);
		for(EmployeeDepartment empDept : empDeptList)
			getHibernateTemplate().delete(empDept);
	}
	
	public void evict(Object object) {
		getHibernateTemplate().evict(object);
	}
	
	public Object load(long id, Class clz) {
		return getHibernateTemplate().get(clz, id);
	}
	
	public Object merge(Object object) {
		return getHibernateTemplate().merge(trackVersion(object));
	}
	
	public void save(Object object) {
		getHibernateTemplate().save(trackVersion(object));
	}
	
	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(trackVersion(object));
	}
	
	public void update(Object object) {
		getHibernateTemplate().update(trackVersion(object));
	}
	
	public List getCityList() {
		return getHibernateTemplate().find("from City city order by city.name");
	}
	
	public List getCompanyList() {
		return getHibernateTemplate().find("from Company company order by company.name");
	}
	
	public List getCompanyListByCity(long cityId) {
		return getHibernateTemplate().find("from Company company WHERE company.city.id = ? order by company.name", cityId);
	}
	
	public List getDepartmentList(long companyId) {
		if(companyId == 0)
			return getHibernateTemplate().find("from Department department order by department.name");
		else
			return getHibernateTemplate().find("from Department department WHERE department.company.id = ? " +
					"order by department.name", companyId);
	}
	
	public List getEmployeeList(long companyId, long officeId) {
		if(companyId == 0 && officeId == 0)
			return getHibernateTemplate().find("from Employee employee order by employee.name");
		else if(officeId == 0)
			return getHibernateTemplate().find("from Employee employee WHERE employee.office.company.id = ? " +
					"order by employee.name", companyId);
		else
			return getHibernateTemplate().find("from Employee employee WHERE employee.office.id = ? " +
					"order by employee.name", officeId);
	}
	
	public List getEmployeeListByCity(long cityId) {
		return getHibernateTemplate().find("from Employee employee WHERE employee.city.id = ? order by employee.name", cityId);
	}
	
	public List getEmployeeListByDepartment(long departmentId) {
		return getHibernateTemplate().find("select distinct employeeDepartment.employee from EmployeeDepartment " +
				"employeeDepartment WHERE employeeDepartment.department.id = ?", departmentId);
	}
	
	public List getEmployeeDepartmentList(long employeeId) {
		return getHibernateTemplate().find("from EmployeeDepartment employeeDepartment " +
				"WHERE employeeDepartment.employee.id = ?", employeeId);
	}
	
	public List getOfficeList(long companyId) {
		if(companyId == 0)
			return getHibernateTemplate().find("from Office office order by office.name");
		else
			return getHibernateTemplate().find("from Office office WHERE office.company.id = ? " +
					"order by office.name", companyId);
	}
	
	public List getOfficeListByCity(long cityId) {
		return getHibernateTemplate().find("from Office office WHERE office.city.id = ? order by office.name", cityId);
	}
	
	public List getAssignDepartmentCount(long count) {
		List<Employee> employeeList = getHibernateTemplate().find("from Employee employee order by employee.name");
		List<EmployeeDepartment> empDeptList = null;
		List<Employee> employeeResult = new ArrayList<Employee>();
		for(Employee employee : employeeList) {
			empDeptList = getHibernateTemplate().find("from EmployeeDepartment employeeDepartment " +
					"WHERE employeeDepartment.employee.id = ?", employee.getId());
			if(empDeptList.size() >= count)
				employeeResult.add(employee);
		}
		return employeeResult;
	}
	
}