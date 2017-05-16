package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;
import test.constant.asia.validation.CityValidator;

@Controller
@RequestMapping("/cityDetails")
public class CityDetailsController {
	
	@Autowired AdminDao adminDao;
	private Validator validator;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("cityId")long cityId, Model model) {
		validator = new CityValidator(adminDao);
		if(cityId == 0)
			model.addAttribute("command", new City());
		else
			model.addAttribute("command", adminDao.load(cityId, City.class));
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") City city, BindingResult result) {
		validator.validate(city, result);
		if (result.hasErrors())
			return "cityDetails";
		else {
			adminDao.merge(city);	
			return "redirect:/cityList.html";
		}
	}
	
}
