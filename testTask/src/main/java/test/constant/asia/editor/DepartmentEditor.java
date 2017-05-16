package test.constant.asia.editor;

import java.beans.PropertyEditorSupport;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Department;

public class DepartmentEditor extends PropertyEditorSupport {
	
	AdminDao adminDao;
	
	public DepartmentEditor(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   	Department department = (Department) adminDao.load(Long.parseLong(text), Department.class);
   	if(department == null)
   		department = new Department();
   	setValue(department);
   }
   
   @Override
   public String getAsText() {
   	Department department = (Department) getValue();
       if (department == null)
           return null;
       else
           return ((Long)department.getId()).toString();
   }
	
}
