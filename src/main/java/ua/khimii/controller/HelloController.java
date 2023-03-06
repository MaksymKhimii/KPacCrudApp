package ua.khimii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kpac")
public class HelloController {
   /* private final KPacService kPacService;

    @Autowired
    public KPacController(KPacService kPacService) {
        this.kPacService = kPacService;
    }

    @GetMapping()
    public ModelAndView getAll(Model model) {
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
        if (model.getAttribute("kpacs") == null) {
            modelAndView.addObject("kpacs", kPacService.getAll());
        }
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        kPacService.delete(id);
        return "redirect:/kpac";
    }

    @GetMapping("/createKpac")
    public String createPage(@ModelAttribute("kpac") KPac kPac) {
        return "create_kpac";
    }

    @PostMapping()
    public String createCPac(@ModelAttribute("kpac") KPac kPac) {
        //TODO change the simple date format to dd-MM-yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);
        kPac.setDate_of_creation(dateNow);
        kPacService.save(kPac);
        return "redirect:/kpac";
    }

    @GetMapping("/sortKPac")
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
    }*/
}
