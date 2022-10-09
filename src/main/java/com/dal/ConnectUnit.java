
package com.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @Ngay : 04/05/2021
 * @Nguyen Dep Trai
 * @Do An Quan Li Cua Hang Ban May Tinh
 */

public class ConnectUnit {
    private ConnectSQL connectSQL = new ConnectSQL();

    public ConnectUnit() {
        try {
            ConnectSQL.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Select * FROM tableName Where Condition ORDER BY orderBy;
    public ResultSet select(String tableName, String condition, String orderby) throws SQLException{
//        System.out.println(tableName);
        
        StringBuilder query = new StringBuilder("SELECT * FROM " + tableName);
        this.addCondition(query,condition);
        this.addOrderBy(query,orderby);
        query.append(";");
        System.out.println(query);
        
        return this.connectSQL.excuteQuery(query.toString());
    }
    public ResultSet select(String tableName, String condition) throws SQLException{
        return select(tableName, condition,null);
    }
    
    public ResultSet select(String tableName) throws SQLException{
        return select(tableName,null);
    }

    private void addCondition(StringBuilder query, String condition) {
        if(condition!=null){
            query.append(" WHERE " + condition);
        }
    }

    private void addOrderBy(StringBuilder query, String orderby) {
        if(orderby!=null){
            query.append("ORDER BY " + orderby);
        }
    }
    public boolean insert(String tableName, HashMap<String,Object> columValues) throws SQLException{
        
        StringBuilder query = new StringBuilder("INSERT INTO "+tableName);
        StringBuilder valuesInsert = new StringBuilder();
        query.append("(");
        for(String key : columValues.keySet()){
            query.append(key + ",");
            valuesInsert.append("'"+columValues.get(key).toString()+"',");
        }
        query = query.delete(query.length()-1,query.length());
        valuesInsert = valuesInsert.delete(valuesInsert.length()-1,valuesInsert.length());
        query.append(") VALUES (" + valuesInsert.toString()+")");
        query.append(";");
        System.out.println(query);
        return this.connectSQL.executeUpdate(query.toString())>0;
        
    }
    
    public boolean update(String tableName, HashMap<String,Object> columValues, String condition) throws SQLException{
        StringBuilder query = new StringBuilder("UPDATE "+tableName+" SET ");

        for(String key : columValues.keySet()){
            query.append(key + " = '"+columValues.get(key).toString()+"',");
        }
        query = query.delete(query.length()-1,query.length());
        this.addCondition(query, condition);
        query.append(";");
        System.out.println(query);
        return this.connectSQL.executeUpdate(query.toString())>0;
    }
    public boolean delete(String tableName, String condition) throws SQLException{
        StringBuilder query = new StringBuilder("DELETE FROM "+tableName);
        this.addCondition(query, condition);
        query.append(";");
        System.out.println(query);
        return this.connectSQL.executeUpdate(query.toString())>0;
    }
    // dem so cot trong result 
    public int getColumCount (ResultSet resultset) throws SQLException{
        return resultset.getMetaData().getColumnCount();
    }
    // lay danh sach ten cot trong result
    public String[] getColumName (ResultSet result) throws SQLException{
        ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
        int columCount = rsmd.getColumnCount();
        String[] list ;
        list = new String[columCount];
        for(int i = 0; i<columCount;i++){
            list[i]=rsmd.getColumnName(i);
        }
        return list;
    }
    
     
}
