package test.constant.asia.editor;

import java.beans.PropertyEditorSupport;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;

public class CityEditor extends PropertyEditorSupport {
	
	AdminDao adminDao;
	
	public CityEditor(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   	City city = (City) adminDao.load(Long.parseLong(text), City.class);
   	if(city == null)
   		city = new City();
   	setValue(city);
   }
   
   @Override
   public String getAsText() {
   	City city = (City) getValue();
       if (city == null)
           return null;
       else
           return ((Long)city.getId()).toString();
   }
	
}
