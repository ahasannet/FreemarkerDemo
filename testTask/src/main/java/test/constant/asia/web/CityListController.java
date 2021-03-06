package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/cityList")
public class CityListController {
	
	@Autowired AdminDao adminDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(Model model) {
		model.addAttribute("cityList", adminDao.getCityList());
		return model;
	}

}
