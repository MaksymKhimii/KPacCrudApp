package ua.khimii.model.entity.filterEntity;

public class SelectAndFilterKPac {
   private String[] sortingTitleArray;
   private String filter;

    public SelectAndFilterKPac(String[] sortingTitleArray, String filter) {
        this.sortingTitleArray = sortingTitleArray;
        this.filter = filter;
    }

    public SelectAndFilterKPac() {
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String[] getSortingTitleArray() {
        return sortingTitleArray;
    }

    public void setSortingTitleArray(String[] sortingTitleArray) {
        this.sortingTitleArray = sortingTitleArray;
    }
}
