package ua.khimii.model.entity.filterEntity;

import ua.khimii.model.entity.KPacSet;

public class CreationKPacSetModel {
    private KPacSet kPacSet;
    private String[] kpacs;

    public KPacSet getkPacSet() {
        return kPacSet;
    }

    public void setkPacSet(KPacSet kPacSet) {
        this.kPacSet = kPacSet;
    }

    public String[] getKpacs() {
        return kpacs;
    }

    public void setKpacs(String[] kpacs) {
        this.kpacs = kpacs;
    }
}
