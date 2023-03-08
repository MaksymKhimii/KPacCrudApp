package ua.khimii.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ua.khimii.model.entity.KPac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;
import ua.khimii.model.service.KPacService;
import ua.khimii.rest.MultipleKPacResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class KPacController {

    @Autowired
    private KPacService kPacService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
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
        modelAndView.setViewName("kpac_main");
        return modelAndView;
    }

    @RequestMapping(value = "/kpacs", method = RequestMethod.GET)
    @ResponseBody
    public MultipleKPacResponse getAllEmployees() {
        List<KPac> allEmployees = kPacService.getAll();
        return new MultipleKPacResponse(allEmployees);
    }

    @RequestMapping(value = "/createKpac", method = RequestMethod.GET)
    public String createPage(@ModelAttribute("kpac") KPac kPac) {
        return "create_kpac";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createCPac(@ModelAttribute("kpac") KPac kPac) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);
        kPac.setDate_of_creation(dateNow);
        kPacService.save(kPac);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id) {
        System.out.println("id: " + id);
        kPacService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/sortKPac", method = RequestMethod.GET)
    @ResponseBody
    public MultipleKPacResponse sortKPacs(@CookieValue("filter") String filter,
                                          @CookieValue("sort_select") String sortSelect) {

        System.out.println("filter: " + filter);
        System.out.println("sort_select: " + sortSelect);
        List<KPac> allEmployees = kPacService.filterAndSort(filter, sortSelect);
        return new MultipleKPacResponse(allEmployees);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ModelAndView sort(@ModelAttribute("myform") SelectAndFilterKPac myform) {
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
        modelAndView.setViewName("sort");
        return modelAndView;
    }
}