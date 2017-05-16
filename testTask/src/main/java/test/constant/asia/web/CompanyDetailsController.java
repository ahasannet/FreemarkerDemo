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
import test.constant.asia.editor.CityEditor;
import test.constant.asia.validation.CompanyValidator;

@Controller
@RequestMapping("/companyDetails")
public class CompanyDetailsController {
	
	@Autowired AdminDao adminDao;
	private Validator validator;
	
	
	@InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       dataBinder.registerCustomEditor(City.class, new CityEditor(adminDao));
   }
	
	@ModelAttribute("cityList")
	public List<City> populateCity() {
		return adminDao.getCityList();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, Model model) {
		validator = new CompanyValidator(adminDao);
		Company company = (Company)adminDao.load(companyId, Company.class);
		if(company == null)
			company = new Company();
		if(company.getCity() == null)
			company.setCity(new City());
		model.addAttribute("command", company);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") Company company, BindingResult result) {
		validator.validate(company, result);
		if (result.hasErrors())
			return "companyDetails";
		else {
			adminDao.merge(company);	
			return "redirect:/companyList.html";
		}
	}
	
}
