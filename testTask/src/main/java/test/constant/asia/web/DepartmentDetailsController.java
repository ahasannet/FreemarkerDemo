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
import test.constant.asia.db.Company;
import test.constant.asia.db.Department;
import test.constant.asia.editor.CompanyEditor;
import test.constant.asia.validation.DepartmentValidator;

@Controller
@RequestMapping("/departmentDetails")
public class DepartmentDetailsController {
	
	@Autowired AdminDao adminDao;
	private long companyId;
	private Validator validator;
	
	
	@InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       dataBinder.registerCustomEditor(Company.class, new CompanyEditor(adminDao));
   }
	
	@ModelAttribute("companyList")
	public List<Company> populateCompany() {
		return adminDao.getCompanyList();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, @RequestParam("departmentId")long departmentId, 
				Model model) {
		validator = new DepartmentValidator(adminDao);
		Department department = (Department)adminDao.load(departmentId, Department.class);
		if(departmentId == 0) {
			department = new Department();
			department.setCompany((Company)adminDao.load(companyId, Company.class));
			if(department.getCompany() == null)
				department.setCompany(new Company());
		}
		if(companyId == 0)
			this.companyId = department.getCompany().getId();
		else
			this.companyId = companyId;
		model.addAttribute("companyId", this.companyId);
		model.addAttribute("command", department);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") Department department, BindingResult result) {
		validator.validate(department, result);
		if (result.hasErrors())
			return "departmentDetails";
		else {
			adminDao.merge(department);	
			return "redirect:/departmentList.html?companyId=" + department.getCompany().getId();
		}
	}
	
}
