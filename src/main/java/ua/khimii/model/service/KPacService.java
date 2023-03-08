package ua.khimii.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.khimii.model.dao.KPacDAO;
import ua.khimii.model.entity.KPac;

import java.util.List;


@Service
public class KPacService {
    private final KPacDAO kPacDAO;

    @Autowired
    public KPacService(KPacDAO kPacDAO) {
        this.kPacDAO = kPacDAO;
    }

    public List<KPac> getAll() {
        return kPacDAO.getAllKPacs();
    }

    public void delete(int id) {
        kPacDAO.delete(id);
    }

    public void save(KPac kPac) {
        kPacDAO.create(kPac);
    }

    public List<KPac> filterAndSort(String filter, String sortSelect) {
        return kPacDAO.filterAndSorting(filter, sortSelect);
    }

    public List<KPac> getKPacSetById(int id) {
        return kPacDAO.getKPacSetById(id);
    }
}
