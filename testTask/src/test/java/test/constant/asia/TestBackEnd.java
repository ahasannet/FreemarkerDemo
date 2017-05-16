package test.constant.asia;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;
import test.constant.asia.db.Company;
import test.constant.asia.db.Department;
import test.constant.asia.db.Employee;
import test.constant.asia.db.EmployeeDepartment;
import test.constant.asia.db.Office;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@TransactionConfiguration(transactionManager="localTransactionManager", defaultRollback = false)
@Transactional
public class TestBackEnd extends TestCase {

	@Autowired AdminDao adminDao;
	
	@Test 
	public void TestSaveUpdateDeleteCity() throws Exception {
		assertNotNull(adminDao);
		// Save City & Count
		int oldCount = adminDao.getCityList().size();
		City city = new City();
		String saveCityName = "Dhaka";
		city.setName(saveCityName);
		adminDao.save(city);
		int postSaveCount = adminDao.getCityList().size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update City
		City updateCity = (City)adminDao.load(city.getId(), City.class);
		adminDao.evict(city);
		assertNotNull(updateCity);
		String updateCityName = "Khulna";
		updateCity.setName(updateCityName);
		adminDao.update(updateCity);
		// Load City & check whether name updated
		City newCity = (City)adminDao.load(updateCity.getId(), City.class);
		adminDao.evict(updateCity);
		assertNotNull(newCity);
		assertEquals(updateCityName, newCity.getName());
		// Delete City & Count
		adminDao.delete(newCity);
		int postDeleteCount = adminDao.getCityList().size();
		assertEquals(oldCount, postDeleteCount);
	}
	
	@Test 
	public void TestSaveUpdateDeleteCompany() throws Exception {
		assertNotNull(adminDao);
		// Save Company & Count
		int oldCount = adminDao.getCompanyList().size();
		// Save City
		City city = new City();
		city.setName("Dhaka");
		adminDao.save(city);
		Company company = new Company();
		String saveCompanyName = "Constant Asia";
		company.setName(saveCompanyName);
		company.setCity(city);
		adminDao.save(company);
		long postSaveCount = adminDao.getCompanyList().size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update Company
		Company updateCompany = (Company)adminDao.load(company.getId(), Company.class);
		adminDao.evict(company);
		assertNotNull(updateCompany);
		String updateCompanyName = "Test Asia";
		updateCompany.setName(updateCompanyName);
		adminDao.update(updateCompany);
		// Load Company & check whether name updated
		Company newCompany = (Company)adminDao.load(updateCompany.getId(), Company.class);
		adminDao.evict(updateCompany);
		assertNotNull(newCompany);
		assertEquals(updateCompanyName, newCompany.getName());
		// Delete Company & Count
		adminDao.delete(newCompany);
		long postDeleteCount = adminDao.getCompanyList().size();
		assertEquals(oldCount, postDeleteCount);
		adminDao.delete(city);
	}
	
	@Test 
	public void TestSaveUpdateDeleteDepartment() throws Exception {
		assertNotNull(adminDao);
		// Save Company
		Company company = new Company();
		company.setName("Constant Asia");
		adminDao.save(company);
		// Save Department & Count
		int oldCount = adminDao.getDepartmentList(company.getId()).size();
		Department department = new Department();
		String saveDepartmentName = "HR";
		department.setName(saveDepartmentName);
		department.setCompany(company);
		adminDao.save(department);
		int postSaveCount = adminDao.getDepartmentList(company.getId()).size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update Department
		Department updateDepartment = (Department)adminDao.load(department.getId(), Department.class);
		adminDao.evict(department);
		assertNotNull(updateDepartment);
		String updateDepartmentName = "ADMIN";
		updateDepartment.setName(updateDepartmentName);
		adminDao.update(updateDepartment);
		// Load Department & check whether name updated
		Department newDepartment = (Department)adminDao.load(updateDepartment.getId(), Department.class);
		adminDao.evict(updateDepartment);
		assertNotNull(newDepartment);
		assertEquals(updateDepartmentName, newDepartment.getName());
		// Delete Department & Count
		adminDao.delete(newDepartment);
		int postDeleteCount = adminDao.getDepartmentList(company.getId()).size();
		assertEquals(oldCount, postDeleteCount);
	// Delete Company
		adminDao.delete(company);
	}
	
	@Test 
	public void TestSaveUpdateDeleteOffice() throws Exception {
	// Save Company
		Company company = new Company();
		company.setName("Constant Asia");
		adminDao.save(company);
		// Save City
		City city = new City();
		city.setName("Dhaka");
		adminDao.save(city);
		// Save Office & Count
		int oldCount = adminDao.getOfficeList(company.getId()).size();
		Office office = new Office();
		String saveOfficeName = "DBBL";
		office.setName(saveOfficeName);
		office.setCompany(company);
		office.setAddress1("Dhanmondi");
		office.setPhone("123");
		office.setCity(city);
		office.setZip("1212");
		adminDao.save(office);
		int postSaveCount = adminDao.getOfficeList(company.getId()).size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update Office
		Office updateOffice = (Office)adminDao.load(office.getId(), Office.class);
		adminDao.evict(office);
		assertNotNull(updateOffice);
		String updateOfficeName = "SCB";
		updateOffice.setName(updateOfficeName);
		adminDao.update(updateOffice);
		// Load Office & check whether name updated
		Office newOffice = (Office)adminDao.load(updateOffice.getId(), Office.class);
		adminDao.evict(updateOffice);
		assertNotNull(newOffice);
		assertEquals(updateOfficeName, newOffice.getName());
		// Delete Office & Count
		adminDao.delete(newOffice);
		int postDeleteCount = adminDao.getOfficeList(company.getId()).size();
		assertEquals(oldCount, postDeleteCount);
		// Delete City & Company
		adminDao.delete(city);
		adminDao.delete(company);
	}
	
	@Test 
	public void TestSaveUpdateDeleteEmployee() throws Exception {
	// Save Company
		Company company = new Company();
		company.setName("Constant Asia");
		adminDao.save(company);
		// Save City
		City city = new City();
		city.setName("Dhaka");
		adminDao.save(city);
		// Save Office
		Office office = new Office();
		office.setName("DBBL");
		office.setCompany(company);
		office.setAddress1("Dhanmondi");
		office.setPhone("123");
		office.setCity(city);
		office.setZip("1212");
		adminDao.save(office);
		// Save Employee & Count
		int oldCount = adminDao.getEmployeeList(company.getId(), 0).size();
		Employee employee = new Employee();
		String saveEmployeeName = "John";
		employee.setName(saveEmployeeName);
		employee.setOffice(office);
		employee.setAddress1("Dhanmondi");
		employee.setPhone("123");
		employee.setCity(city);
		employee.setZip("1212");
		adminDao.save(employee);
		int postSaveCount = adminDao.getEmployeeList(company.getId(), 0).size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update Employee
		Employee updateEmployee = (Employee)adminDao.load(employee.getId(), Employee.class);
		adminDao.evict(office);
		assertNotNull(employee);
		String updateEmployeeName = "Josheph";
		updateEmployee.setName(updateEmployeeName);
		adminDao.update(updateEmployee);
		// Load Employee & check whether name updated
		Employee newEmployee = (Employee)adminDao.load(updateEmployee.getId(), Employee.class);
		adminDao.evict(updateEmployee);
		assertNotNull(newEmployee);
		assertEquals(updateEmployeeName, newEmployee.getName());
		// Delete Employee & Count
		adminDao.delete(newEmployee);
		int postDeleteCount = adminDao.getEmployeeList(company.getId(), 0).size();
		assertEquals(oldCount, postDeleteCount);
		// Delete City, Company & Office
		adminDao.delete(city);
		adminDao.delete(company);
	}
	
	@Test 
	public void TestAssignDepartmentsToEmployee() throws Exception {
	// Save Company
		Company company = new Company();
		company.setName("Constant Asia");
		adminDao.save(company);
		// Save Department
		Department department = new Department();
		department.setName("HR");
		department.setCompany(company);
		adminDao.save(department);
	// Save 2nd Department
		Department department2 = new Department();
		department2.setName("HR");
		department2.setCompany(company);
		adminDao.save(department2);
		// Save City
		City city = new City();
		city.setName("Dhaka");
		adminDao.save(city);
		// Save Office
		Office office = new Office();
		office.setName("DBBL");
		office.setCompany(company);
		office.setAddress1("Dhanmondi");
		office.setPhone("123");
		office.setCity(city);
		office.setZip("1212");
		adminDao.save(office);
		// Save Employee
		Employee employee = new Employee();
		employee.setName("John");
		employee.setOffice(office);
		employee.setAddress1("Dhanmondi");
		employee.setPhone("123");
		employee.setCity(city);
		employee.setZip("1212");
		adminDao.save(employee);
		// Save EmployeeDocument & Count
		int oldCount = adminDao.getEmployeeDepartmentList(employee.getId()).size();
		EmployeeDepartment employeeDepartment = new EmployeeDepartment();
		employeeDepartment.setDepartment(department);
		employeeDepartment.setEmployee(employee);
		adminDao.save(employeeDepartment);
		int postSaveCount = adminDao.getEmployeeDepartmentList(employee.getId()).size();
		assertEquals(oldCount + 1, postSaveCount);
		// Load & Update EmployeeDocument
		EmployeeDepartment updateEmployeeDepartment = (EmployeeDepartment)adminDao.load(employeeDepartment.getId(), EmployeeDepartment.class);
		adminDao.evict(employeeDepartment);
		assertNotNull(employee);
		updateEmployeeDepartment.setDepartment(department2);
		adminDao.update(updateEmployeeDepartment);
		// Load EmployeeDocument & check whether name updated
		EmployeeDepartment newEmployeeDepartment = (EmployeeDepartment)adminDao.load(updateEmployeeDepartment.getId(), EmployeeDepartment.class);
		adminDao.evict(updateEmployeeDepartment);
		assertNotNull(newEmployeeDepartment);
		assertEquals(department2.getId(), newEmployeeDepartment.getDepartment().getId());
		// Delete Employee & Count
		adminDao.delete(newEmployeeDepartment);
		int postDeleteCount = adminDao.getEmployeeDepartmentList(employee.getId()).size();
		assertEquals(oldCount, postDeleteCount);
		// Delete City, Company, Office & EMployee
		adminDao.delete(city);
		adminDao.delete(company);
	}
	
}