package com.dto;

import java.util.Collections;
import java.util.Vector;

public class DTO {
	protected String[] data=null;
	public DTO(){

	}

	public DTO(String[] data) {
		this.data = data;
	}

	public String[] getData() {
		return data;
	}

	public static DTO getDTO(String[] info) {
		return new DTO(info);
	}

	public Vector<String> toTable() {
		Vector<String> row = new Vector<>();
		Collections.addAll(row, getData());
		return row;
	}

	public String toString() {
		StringBuilder temp = new StringBuilder();
		for (String s : data)
			temp.append("|").append(s);
		return temp.toString();
	}
}
