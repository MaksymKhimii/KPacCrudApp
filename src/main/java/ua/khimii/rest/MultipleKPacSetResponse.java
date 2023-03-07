package ua.khimii.rest;

import ua.khimii.model.entity.KPac;
import ua.khimii.model.entity.KPacSet;

import java.util.List;

public class MultipleKPacSetResponse {
    private int total_count = 0;
    private int pos = 0;
    private List<KPacSet> data;

    public MultipleKPacSetResponse(List<KPacSet> data) {
        this.data = data;
        total_count = data.size();
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<KPacSet> getData() {
        return data;
    }

    public void setData(List<KPacSet> data) {
        this.data = data;
    }
}

