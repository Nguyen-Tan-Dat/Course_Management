package com.bll;

import com.dal.CoreDAL;
import com.dto.DTO;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Vector;


public abstract class BLL {

	protected final CoreDAL dal;
	protected HashMap<String, DTO> list;

	protected int newID;

	public BLL(CoreDAL dal) {
		this.dal = dal;
		readTable();
		newID = this.dal.getMaxId() + 1;
	}
	public DTO getDTO(){
		return this.dal.getDTO();
	}
	public void readTable() {
		this.list= dal.read();
	}

	public void add(DTO dto) {
		if (dal.add(newID + "", dto)) {
			getList().put("" + newID++, dto);
		}
	}

	public HashMap<String, DTO> getList() {
		return list;
	}

	public void add(String id, DTO dto) {
		if (dal.add(id, dto)) {
			getList().put(id, dto);
		}
	}

	public void update(String id, DTO info) {
		delete(id);
		add(id, info);
	}

	public boolean delete(String id) {
		if (dal.delete(id)){
			getList().remove(id);
			return true;
		}
		return false;
	}

	public HashMap<String, DTO> searchAll(String info) {
		HashMap<String, DTO> temp = new HashMap<>();
		for (var i : getList().keySet())
			if (list.get(i).toString().contains(info) || (i + "").contains(info))
				temp.put(i, getList().get(i));
		return temp;
	}

	@Override
	public String toString() {
		if (getList().isEmpty()) return "";
		StringBuilder temp = new StringBuilder();
		for (var i : getList().keySet()) {
			temp.append(i).append(getList().get(i).toString()).append("\n");
		}
		return temp.toString();
	}

	public DefaultTableModel toTable() {
		return toTable(getList());
	}

	public DefaultTableModel toTable(HashMap<String, DTO> list) {
		return toTable(list, dal.columnNames());
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
}
