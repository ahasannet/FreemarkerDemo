package test.constant.asia.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;

public class CityValidator implements Validator {

	private AdminDao adminDao;
	private List<City> cityList = null;
	
	
	public CityValidator(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public boolean supports(Class clazz) {
		return City.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		City city = (City) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required");
		cityList = adminDao.getCityList();
		for(City oldCity : cityList) {
			if(city.getId() == oldCity.getId()) continue;
			if(city.getName().equalsIgnoreCase(oldCity.getName())) {
				errors.rejectValue("name", "required", "'City Name' exist");
				break;
			}
		}
	}

}
