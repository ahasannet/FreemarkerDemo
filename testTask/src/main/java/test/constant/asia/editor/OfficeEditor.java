package test.constant.asia.editor;

import java.beans.PropertyEditorSupport;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Office;

public class OfficeEditor extends PropertyEditorSupport {
	
	AdminDao adminDao;
	
	public OfficeEditor(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   	Office office = (Office) adminDao.load(Long.parseLong(text), Office.class);
   	if(office == null)
   		office = new Office();
   	setValue(office);
   }
   
   @Override
   public String getAsText() {
   	Office office = (Office) getValue();
       if (office == null)
           return null;
       else
           return ((Long)office.getId()).toString();
   }
	
}
