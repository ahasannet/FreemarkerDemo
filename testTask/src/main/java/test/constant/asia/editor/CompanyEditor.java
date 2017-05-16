package test.constant.asia.editor;

import java.beans.PropertyEditorSupport;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Company;

public class CompanyEditor extends PropertyEditorSupport {
	
	AdminDao adminDao;
	
	public CompanyEditor(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   	Company company = (Company) adminDao.load(Long.parseLong(text), Company.class);
   	if(company == null)
   		company = new Company();
   	setValue(company);
   }
   
   @Override
   public String getAsText() {
       Company company = (Company) getValue();
       if (company == null)
           return null;
       else
           return ((Long)company.getId()).toString();
   }
	
}
