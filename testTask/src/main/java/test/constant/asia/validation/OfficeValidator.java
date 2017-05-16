package test.constant.asia.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Office;

public class OfficeValidator implements Validator {

	private AdminDao adminDao;
	private List<Office> officeList = null;
	
	
	public OfficeValidator(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public boolean supports(Class clazz) {
		return Office.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		Office office = (Office) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "required", "'Address 1' is required");
		if(office.getCompany() == null || office.getCompany().getId() == 0)
			errors.rejectValue("company", "required", "Company is required");
		if(office.getCity() == null || office.getCity().getId() == 0)
			errors.rejectValue("city", "required", "City is required");
		if(office.getCompany() != null) {
			officeList = adminDao.getOfficeList(office.getCompany().getId());
			for(Office oldOffice : officeList) {
				if(office.getId() == oldOffice.getId()) continue;
				if(office.getName().equalsIgnoreCase(oldOffice.getName())) {
					errors.rejectValue("name", "required", "'Office Name' exist");
					break;
				}
			}
		}
	}

}
