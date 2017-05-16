package test.constant.asia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;

@Controller
@RequestMapping("/delete")
public class DeleteController {
	
	@Autowired AdminDao adminDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String onDelete(@RequestParam("dbId")long dbId, @RequestParam("clazz")String clazz, 
			@RequestParam("view")String view) throws Exception {
		adminDao.delete(adminDao.load(dbId, Class.forName("test.constant.asia.db." + clazz)));
		return "redirect:/" + view;
	}

}
