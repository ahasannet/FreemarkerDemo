package test.constant.asia.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Company;

public class CompanyValidator implements Validator {

	private AdminDao adminDao;
	private List<Company> companyList = null;
	
	public CompanyValidator(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public boolean supports(Class clazz) {
		return Company.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		Company company = (Company) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required");
		if(company.getCity() == null || company.getCity().getId() == 0)
			errors.rejectValue("city", "required", "City is required");
		companyList = adminDao.getCompanyList();
		for(Company oldCompany : companyList) {
			if(company.getId() == oldCompany.getId()) continue;
			if(company.getName().equalsIgnoreCase(oldCompany.getName())) {
				errors.rejectValue("name", "required", "'Company Name' exist");
				break;
			}
		}
	}

}
