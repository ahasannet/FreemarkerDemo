package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Employee;

@Controller
@RequestMapping("/deleteEmployee")
public class DeleteEmployeeController {
	
	@Autowired AdminDao adminDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String onDelete(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, 
			@RequestParam("employeeId")long employeeId) {
		Employee employee = (Employee)adminDao.load(employeeId, Employee.class);
		adminDao.delete(employee);
		return "redirect:/employeeList.html?companyId=" + companyId + "&officeId=" + officeId;
	}

}
