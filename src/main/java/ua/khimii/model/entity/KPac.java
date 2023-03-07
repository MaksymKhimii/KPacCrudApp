package ua.khimii.model.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class KPac implements Serializable {
    private int id;
    private String title;
    private String description;
    private String date_of_creation;
    private String delete;

    public KPac(int id, String title, String description, String date_of_creation, String delete) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_of_creation = date_of_creation;
        this.delete = delete;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public KPac() {

    }
    public KPac(String title, String description, String date_of_creation) {
        this.title = title;
        this.description = description;
        this.date_of_creation = date_of_creation;
    }

    public KPac(int id, String title, String description, String date_of_creation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_of_creation = date_of_creation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(String date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    @Override
    public String toString() {
        return "KPac{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_of_creation=" + date_of_creation +
                '}';
    }
}
