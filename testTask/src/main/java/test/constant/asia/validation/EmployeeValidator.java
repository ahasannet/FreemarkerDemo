package test.constant.asia.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import test.constant.asia.db.Employee;

public class EmployeeValidator implements Validator {

	public boolean supports(Class clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		Employee employee = (Employee) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "required", "'Address 1' is required");
		if(employee.getOffice() == null || employee.getOffice().getId() == 0)
			errors.rejectValue("office", "required", "Office is required");
		if(employee.getCity() == null || employee.getCity().getId() == 0)
			errors.rejectValue("city", "required", "City is required");
	}

}
