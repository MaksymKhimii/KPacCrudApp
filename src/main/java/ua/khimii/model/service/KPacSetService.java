package ua.khimii.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.khimii.model.dao.KPacSetDAO;
import ua.khimii.model.entity.KPacSet;
import ua.khimii.model.entity.filterEntity.CreationKPacSetModel;

import java.util.List;


@Service
public class KPacSetService {
    private final KPacSetDAO kPacSetDAO;

    @Autowired
    public KPacSetService(KPacSetDAO kPacSetDAO) {
        this.kPacSetDAO = kPacSetDAO;
    }

    public List<KPacSet> getALl() {
        return kPacSetDAO.getALl();
    }

    public void delete(int id) {
        kPacSetDAO.delete(id);
    }

    public List<KPacSet> filterAndSort(String filter, String sortSelect) {
        return kPacSetDAO.filterAndSort(filter, sortSelect);
    }

    public void save(CreationKPacSetModel kPacSet) {
        kPacSetDAO.createNewKPacSet(kPacSet.getkPacSet().getTitle());
        kPacSetDAO.save(kPacSetDAO.getLastKPacSetId(), kPacSet.getKpacs());
    }
}
