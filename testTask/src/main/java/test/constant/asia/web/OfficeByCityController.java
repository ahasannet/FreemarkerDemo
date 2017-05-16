package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/officeByCity")
public class OfficeByCityController {
	
	@Autowired AdminDao adminDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("cityId")long cityId, @RequestParam("city")String city, Model model) {
		model.addAttribute("city", city);
		model.addAttribute("officeList", adminDao.getOfficeListByCity(cityId));
		return model;
	}

}
