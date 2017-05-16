package test.constant.asia.editor;

import java.beans.PropertyEditorSupport;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Employee;

public class EmployeeEditor extends PropertyEditorSupport {
	
	AdminDao adminDao;
	
	public EmployeeEditor(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   	Employee employee = (Employee) adminDao.load(Long.parseLong(text), Employee.class);
   	if(employee == null)
   		employee = new Employee();
   	setValue(employee);
   }
   
   @Override
   public String getAsText() {
   	Employee employee = (Employee) getValue();
       if (employee == null)
           return null;
       else
           return ((Long)employee.getId()).toString();
   }
	
}
