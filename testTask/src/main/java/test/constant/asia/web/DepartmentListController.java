package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/departmentList")
public class DepartmentListController {
	
	@Autowired AdminDao adminDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, Model model) {
		model.addAttribute("companyId", companyId);
		model.addAttribute("departmentList", adminDao.getDepartmentList(companyId));
		return model;
	}

}
