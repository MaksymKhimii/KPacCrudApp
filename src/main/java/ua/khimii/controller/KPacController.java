package ua.khimii.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ua.khimii.model.entity.KPac;
import ua.khimii.model.dao.KPacDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;
import ua.khimii.model.service.KPacService;
import ua.khimii.rest.MultipleEmployeeResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class KPacController {
	
	private static final Logger logger = LoggerFactory.getLogger(KPacController.class);

	@Autowired
	private KPacService kPacService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		List<String> values = new ArrayList<>();
		values.add("id");
		values.add("title");
		values.add("description");
		values.add("creation date");
		List<String> filter = new ArrayList<>();
		filter.add("ascending");
		filter.add("descending");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myform", new SelectAndFilterKPac());
		modelAndView.addObject("filter", filter);
		boolean confirm1 = false;
		modelAndView.addObject("sort_select", values);
		modelAndView.addObject("confirm1", confirm1);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value="/kpacs", method=RequestMethod.GET)
	@ResponseBody
	public MultipleEmployeeResponse getAllEmployees(Model model) {
		logger.info("Inside getAllEmployees() method...");
		System.out.println("yees");
		System.out.println(kPacService.getAll());
		List<KPac> allEmployees = kPacService.getAll();
		return new MultipleEmployeeResponse(allEmployees);
	}

	@RequestMapping(value = "/sortKPac", method = RequestMethod.GET)
	public ModelAndView sortKPacs(@ModelAttribute("myform") SelectAndFilterKPac myform,
								  Model model) {
		model.addAttribute("kpacs", kPacService.filterAndSort(myform));
		List<String> values = new ArrayList<>();
		values.add("id");
		values.add("title");
		values.add("description");
		values.add("creation date");
		List<String> filter = new ArrayList<>();
		filter.add("ascending");
		filter.add("descending");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myform", new SelectAndFilterKPac());
		modelAndView.addObject("filter", filter);
		modelAndView.addObject("sort_select", values);
		modelAndView.addObject("kpacs", kPacService.filterAndSort(myform));
		modelAndView.setViewName("kpac_main");
		return modelAndView;
	}

	@RequestMapping(value = "/createKpac", method = RequestMethod.GET)
	public String createPage(@ModelAttribute("kpac") KPac kPac) {
		return "create_kpac";
	}

	/*@RequestMapping(value="/employee/{uid}", method=RequestMethod.GET)
	@ResponseBody
	public Employee getEmployeeByUID(@PathVariable("uid") String uid) {
		Employee myEmployee = EmployeeRepository.getEmployeeByUID(uid);
		
		if (myEmployee != null) {
			logger.info("Inside getEmployeeByUID, returned: " + myEmployee.toString());
		} else {
			logger.info("Inside getEmployeeByUID, UID: " + uid + ", NOT FOUND!");
		}
		
		return myEmployee; 
	}

	@RequestMapping(value="/employee/delete/{uid}", method=RequestMethod.DELETE)
	@ResponseBody
	public Employee deleteEmployeeByUID(@PathVariable("uid") String uid) {
		
		Employee myEmployee = EmployeeRepository.deleteEmployee(uid);
		
		if (myEmployee != null) {
			logger.info("Inside deleteEmployeeByUID, deleted: " + myEmployee.toString());
		} else {
			logger.info("Inside deleteEmployeeByUID, UID: " + uid + ", NOT FOUND!");
		}
		
		return myEmployee;
	}
	
	@RequestMapping(value="/employee/update/{uid}", method=RequestMethod.PUT)
	@ResponseBody
	public Employee updateEmployeeByUID(@PathVariable("uid") String uid, @ModelAttribute("employee") Employee employee) {
		Employee myEmployee = EmployeeRepository.updateEmployee(uid, employee);
		
		if (myEmployee != null) {
			logger.info("Inside updateEmployeeByUID, updated: " + myEmployee.toString());
		} else {
			logger.info("Inside updateEmployeeByUID, UID: " + uid + ", NOT FOUND!");
		}
		
		return myEmployee;
	}

	@RequestMapping(value="/employee/addEmployee", method=RequestMethod.POST)
	@ResponseBody
	public Employee addEmployee(@ModelAttribute("employee") Employee employee) {
		
		if (employee.getUid() != null && employee.getUid().length() > 0) {
			logger.info("Inside addEmployee, adding: " + employee.toString());
			EmployeeRepository.addEmployee(employee);
		} else {
			logger.info("Failed to insert...");
		}
		
		return employee;
	}	*/
}
