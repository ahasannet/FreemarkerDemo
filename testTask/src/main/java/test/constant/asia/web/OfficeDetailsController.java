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
import test.constant.asia.db.Company;
import test.constant.asia.db.Office;
import test.constant.asia.editor.CityEditor;
import test.constant.asia.editor.CompanyEditor;
import test.constant.asia.validation.OfficeValidator;

@Controller
@RequestMapping("/officeDetails")
public class OfficeDetailsController {
	
	@Autowired AdminDao adminDao;
	private Validator validator;
	private long companyId;
	
	
	@InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       dataBinder.registerCustomEditor(Company.class, new CompanyEditor(adminDao));
       dataBinder.registerCustomEditor(City.class, new CityEditor(adminDao));
   }
	
	@ModelAttribute("companyList")
	public List<Company> populateCompany() {
		return adminDao.getCompanyList();
	}
	
	@ModelAttribute("cityList")
	public List<City> populateCity() {
		return adminDao.getCityList();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, 
				Model model) {
		 validator = new OfficeValidator(adminDao);
		Office office = (Office)adminDao.load(officeId, Office.class);
		if(office == null) {
			office = new Office();
			office.setCompany((Company)adminDao.load(companyId, Company.class));
			if(office.getCompany() == null)
				office.setCompany(new Company());
		}
		if(companyId == 0 && office.getCompany() != null)
			this.companyId = office.getCompany().getId();
		else
			this.companyId = companyId;
		if(office.getCity() == null)
			office.setCity(new City());
		model.addAttribute("companyId", this.companyId);
		model.addAttribute("command", office);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") Office office, BindingResult result) {
		if(companyId == 0)
			companyId = office.getCompany().getId();
		validator.validate(office, result);
		if (result.hasErrors())
			return "officeDetails";
		else {
			adminDao.merge(office);	
			return "redirect:/officeList.html?companyId=" + office.getCompany().getId();
		}	
	}
	 
}
