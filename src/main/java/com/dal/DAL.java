package com.dal;

import com.dto.DTO;
import com.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class DAL {

        public ConnectSQL dal = new ConnectSQL();
        public ConnectUnit sql = new ConnectUnit();
        
	protected Database database;
	private final DTO dto;

	public DAL(String tableName, DTO dto) {
		database = new Database(tableName);
		this.dto = dto;
		database.connect();
	}
	public DAL(String tableName){
		database = new Database(tableName);
		this.dto = new DTO();
		database.connect();
	}

	public Vector<String> columnNames() {
		Vector<String> result = new Vector<>();
		for (var name : database.getFields()) {
			String temp = name.replace("_", " ");
			result.add((temp.charAt(0) + "").toUpperCase() + temp.substring(1));
		}
		return result;
	}

	public HashMap<String, DTO> read(String query) {
		HashMap<String, DTO> list = new HashMap<>();
		try {
			ResultSet resultSet = database.readTable(query);
			int numberOfAttributes = resultSet.getMetaData().getColumnCount() - 1;
			while (resultSet.next()) {
				String[] temp = new String[numberOfAttributes];
				for (int i = 0; i < numberOfAttributes; i++)
					temp[i] = resultSet.getString(i + 2);
				list.put(resultSet.getString(1), dto.getDTO(temp));
			}
		} catch (SQLException sqlException) {
			System.out.println("Error: read table " + database.getNameTable());
			sqlException.printStackTrace();
		}
		return list;
	}

	public boolean add(String id, String[] infos) {
		StringBuilder query = new StringBuilder("insert into " + database.getNameTable() + " values('").append(id).append("'");
		for (String info : infos)
			query.append(",'").append(info).append("'");
		query.append(")");
		return database.execute(query.toString());
	}


	public HashMap<String, DTO> read() {
		return read("select * from " + database.getNameTable());
	}

	public boolean delete(String id) {
		return database.delete((byte) 0, id);
	}

	public int getMaxId() {
		return database.getMaxId();
	}
}
