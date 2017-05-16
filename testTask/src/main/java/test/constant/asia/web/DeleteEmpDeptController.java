package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.EmployeeDepartment;

@Controller
@RequestMapping("/deleteEmpDept")
public class DeleteEmpDeptController {
	
	@Autowired AdminDao adminDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String onDelete(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, 
			@RequestParam("employeeId")long employeeId, @RequestParam("empDeptId")long empDeptId) {
		EmployeeDepartment employeeDepartment = (EmployeeDepartment)adminDao.load(empDeptId, EmployeeDepartment.class);
		adminDao.delete(employeeDepartment);
		return "redirect:/empDeptList.html?companyId=" + companyId + "&officeId=" + officeId + "&employeeId=" + employeeId;
	}

}
