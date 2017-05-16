package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/employeeList")
public class EmployeeListController {
	
	@Autowired AdminDao adminDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, Model model) {
		model.addAttribute("companyId", companyId);
		model.addAttribute("officeId", officeId);
		model.addAttribute("employeeList", adminDao.getEmployeeList(companyId, officeId));
		return model;
	}

}
