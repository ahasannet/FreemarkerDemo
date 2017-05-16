package test.constant.asia.db;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emp_dept")
public class EmployeeDepartment extends ParentModel {

	private Department department;
	private Employee employee;

	public EmployeeDepartment() { }

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department", nullable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
