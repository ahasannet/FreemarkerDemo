package test.constant.asia.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;
import test.constant.asia.db.Employee;
import test.constant.asia.db.Office;
import test.constant.asia.editor.CityEditor;
import test.constant.asia.editor.OfficeEditor;
import test.constant.asia.validation.EmployeeValidator;

@Controller
@RequestMapping("/employeeDetails")
public class EmployeeDetailsController {
	
	@Autowired AdminDao adminDao;
	private Validator validator = new EmployeeValidator();
	private long companyId;
	private long officeId;
	
	
	@InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       dataBinder.registerCustomEditor(Office.class, new OfficeEditor(adminDao));
       dataBinder.registerCustomEditor(City.class, new CityEditor(adminDao));
   }
	
	@ModelAttribute("cityList")
	public List<City> populateCity() {
		return adminDao.getCityList();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, 
			@RequestParam("employeeId")long employeeId, Model model) {
		Employee employee = (Employee)adminDao.load(employeeId, Employee.class);
		if(employee == null) {
			employee = new Employee();
			employee.setOffice((Office)adminDao.load(officeId, Office.class));
			if(employee.getOffice() == null)
				employee.setOffice(new Office());
		}
		if(companyId == 0 && employee.getOffice() != null && employee.getOffice().getCompany() != null)
			this.companyId = employee.getOffice().getCompany().getId();
		else
			this.companyId = companyId;
		if(officeId == 0 && employee.getOffice() != null)
			this.officeId = employee.getOffice().getId();
		else
			this.officeId = officeId;
		if(employee.getCity() == null)
			employee.setCity(new City());
		model.addAttribute("companyId", this.companyId);
		model.addAttribute("officeId", this.officeId);
		model.addAttribute("officeList", adminDao.getOfficeList(this.companyId));
		model.addAttribute("command", employee);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") Employee employee, BindingResult result) {
		validator.validate(employee, result);
		if (result.hasErrors())
			return "employeeDetails";
		else {
			adminDao.merge(employee);	
			return "redirect:/employeeList.html?companyId=" + employee.getOffice().getCompany().getId() + 
					 "&officeId=" + employee.getOffice().getId();
		}	
	}
	 
}
