package ua.khimii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.khimii.model.entity.KPacSet;
import ua.khimii.model.entity.filterEntity.CreationKPacSetModel;
import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;
import ua.khimii.model.service.KPacService;
import ua.khimii.model.service.KPacSetService;
import ua.khimii.rest.MultipleKPacResponse;
import ua.khimii.rest.MultipleKPacSetResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/sets")
public class KPacSetController {
    private final KPacSetService kPacSetService;
    private final KPacService kPacService;

    @Autowired
    public KPacSetController(KPacSetService kPacSetService, KPacService kPacService) {
        this.kPacSetService = kPacSetService;
        this.kPacService = kPacService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<String> values = new ArrayList<>();
        values.add("id");
        values.add("title");
        List<String> filter = new ArrayList<>();
        filter.add("ascending");
        filter.add("descending");
        modelAndView.addObject("sort_select", values);
        modelAndView.addObject("filterForm", new SelectAndFilterKPac());
        modelAndView.addObject("filter", filter);
        modelAndView.setViewName("/sets");
        modelAndView.addObject("set", kPacSetService.getALl());
        return modelAndView;
    }


    @RequestMapping(value = "/sets", method = RequestMethod.GET)
    @ResponseBody
    public MultipleKPacSetResponse getAllSets(Model model) {
        List<KPacSet> sets = kPacSetService.getALl();
        return new MultipleKPacSetResponse(sets);
    }

    @RequestMapping(value = "/createKPacSet", method = RequestMethod.GET)
    public String createPage(@ModelAttribute("set") CreationKPacSetModel pac,
                             Model model) {
        model.addAttribute("kpacs_for_set", kPacService.getAll());
        model.addAttribute("arrr", new String[]{});
        return "/create_kpac_set";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createKPacSet(@ModelAttribute("set") CreationKPacSetModel pac) {
        kPacSetService.save(pac);
        return "redirect:/sets/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MultipleKPacResponse getKPacSetById(@PathVariable("id") String id) {
        return new MultipleKPacResponse(kPacService.getKPacSetById(Integer.parseInt(id)));
    }

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") String id, Model model) {
        model.addAttribute("set", kPacService.getKPacSetById(Integer.parseInt(id)));
        return "set";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id) {
        System.out.println("id: " + id);
        kPacSetService.delete(Integer.parseInt(id));
        return "redirect:/sets/";
    }

    @RequestMapping(value = "/sortKPacSets", method = RequestMethod.GET)
    @ResponseBody
    public MultipleKPacSetResponse sortKPacs(@CookieValue("filterSet") String filter,
                                             @CookieValue("sort_selectSet") String sortSelect) {

        System.out.println("filterSet: " + filter);
        System.out.println("sort_selectSet: " + sortSelect);
        List<KPacSet> allEmployees = kPacSetService.filterAndSort(filter, sortSelect);
        return new MultipleKPacSetResponse(allEmployees);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ModelAndView sort(@ModelAttribute("myform") SelectAndFilterKPac myform,
                             Model model) {
        /*	model.addAttribute("kpacs", kPacService.filterAndSort(myform));*/
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
        modelAndView.setViewName("sortedSets");
        return modelAndView;
    }
}