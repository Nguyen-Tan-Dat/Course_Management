package com.dal;

import com.uitilize.Database;

public class DAL {

        public ConnectSQL dal = new ConnectSQL();
        public ConnectUnit sql = new ConnectUnit();
        
	protected Database database;

	public DAL(String tableName){
		database = new Database(tableName);
		database.connect();
	}

}
