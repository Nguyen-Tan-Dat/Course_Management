package com.bll;

import com.dal.ConnectSQL;
import com.dal.ConnectUnit;
import com.dal.DAL;
import com.dto.DTO;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Vector;


public class BLL {

	protected final com.dal.DAL DAL;
	protected HashMap<String, DTO> list;

	public void setList(HashMap<String, DTO> list) {
		this.list = list;
	}

	protected int newID;

	public BLL(DAL DAL) {
		this.DAL = DAL;
		readTable();
		newID = DAL.getMaxId() + 1;
	}
	public BLL(String tableName){
		this(new DAL(tableName));
	}

	public DTO getObject(String key) {
		return getList().get(key);
	}

	public void readTable() {
		setList(DAL.read());
	}

	public void add(DTO dto) {
		if (DAL.add(newID + "", dto.getData())) {
			getList().put("" + newID++, DTO.getDTO(dto.getData()));
		}
	}

	public HashMap<String, DTO> getList() {
		return list;
	}

	public int getNewID() {
		return newID;
	}

	public void add(String id, DTO info) {
		if (DAL.add(id, info.getData())) {
			getList().put(id, DTO.getDTO(info.getData()));
		}
	}

	public void update(String id, DTO info) {
		delete(id);
		add(id, info);
	}

	public boolean delete(String id) {
		if (DAL.delete(id)){
			getList().remove(id);
			return true;
		}
		return false;
	}

	public HashMap<String, DTO> searchAllAttributes(String info, HashMap<String, DTO> list) {
		HashMap<String, DTO> temp = new HashMap<>();
		for (var i : getList().keySet())
			if (list.get(i).toString().contains(info) || (i + "").contains(info))
				temp.put(i, getList().get(i));
		return temp;
	}

	public HashMap<String, DTO> searchAllAttributes(String info) {
		return searchAllAttributes(info, this.list);
	}

	@Override
	public String toString() {
		if (getList().isEmpty()) return "";
		StringBuilder temp = new StringBuilder();
		for (String i : getList().keySet()) {
			temp.append(i).append(getList().get(i).toString()).append("\n");
		}
		return temp.toString();
	}

	public DefaultTableModel toTable() {
		return toTable(getList());
	}

	public DefaultTableModel toTable(HashMap<String, DTO> list) {
		return toTable(list, DAL.columnNames());
	}

	public DefaultTableModel toTable(HashMap<String, DTO> list, Vector<String> header) {
		Vector<Vector<String>> body = new Vector<>();
		for (var id : list.keySet()) {
			Vector<String> row = getList().get(id).toTable();
			row.add(0, id + "");
			body.add(row);
		}
		return new DefaultTableModel(body, header);
	}

	public Vector<String> toComboBox() {
		var names = DAL.columnNames();
		var result = new Vector<String>();
		names.add(0, "All field");
		for (var i : names) {
			result.add("Search by " + i);
		}
		return result;
	}
}
