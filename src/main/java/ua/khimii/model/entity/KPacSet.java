package ua.khimii.model.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class KPacSet implements Serializable {
    private int id;
    private String title;
    private List<KPac> kPacList;


    public KPacSet(int id, String title) {
        this.id = id;
        this.title = title;
        kPacList = new ArrayList<>();
    }

    public KPacSet(int id, String title, List<KPac> kPacList) {
        this.id = id;
        this.title = title;
        this.kPacList = kPacList;
    }

    public KPacSet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "KPacSet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public List<KPac> getKPacList() {
        return kPacList;
    }

    public void setKPacList(List<KPac> kPacList) {
        this.kPacList = kPacList;
    }
}
