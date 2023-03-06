package ua.khimii.rest;

import java.util.List;

import ua.khimii.model.entity.KPac;

public class MultipleEmployeeResponse {
	private int total_count = 0;
	private int pos = 0;
	private List<KPac> data;
	
	public MultipleEmployeeResponse(List<KPac> data) {
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

	public List<KPac> getData() {
		return data;
	}
	public void setData(List<KPac> data) {
		this.data = data;
	}
}
