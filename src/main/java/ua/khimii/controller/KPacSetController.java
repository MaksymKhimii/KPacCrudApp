package ua.khimii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;
import ua.khimii.model.service.KPacService;
import ua.khimii.model.service.KPacSetService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sets")
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

/*
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        kPacSetService.delete(id);
        return "redirect:/sets";
    }
*/

  /*  @GetMapping("/sort")
    public ModelAndView sortKPacSet(@ModelAttribute("filterForm") SelectAndFilterKPac filterKPacSet) {
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
        modelAndView.addObject("set", kPacSetService.filterAndSort(filterKPacSet));
        modelAndView.setViewName("sets");
        return modelAndView;
    }

    @GetMapping("/createKPacSet")
    public String createPage(@ModelAttribute("set") CreationKPacSetModel pac,
                             Model model) {
        model.addAttribute("kpacs_for_set", kPacService.getAll());
        model.addAttribute("arrr", new String[]{});
        return "/create_kpac_set";
    }

    @PostMapping()
    public String createKPacSet(@ModelAttribute("set") CreationKPacSetModel pac) {
        kPacSetService.save(pac);
        return "redirect:/sets";
    }

    @GetMapping("/set/{id}")
    public ModelAndView getKPacSetById(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("kpacs_by_id",
                kPacService.getKPacSetById(Integer.parseInt(id)));
        modelAndView.setViewName("set");
        return modelAndView;
    }*/
}
