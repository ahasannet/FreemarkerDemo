package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/employeeByDepartment")
public class EmployeeByDepartmentController {
	
	@Autowired AdminDao adminDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("departmentId")long departmentId, @RequestParam("department")String department, 
			Model model) {
		model.addAttribute("department", department);
		model.addAttribute("employeeList", adminDao.getEmployeeListByDepartment(departmentId));
		return model;
	}

}
