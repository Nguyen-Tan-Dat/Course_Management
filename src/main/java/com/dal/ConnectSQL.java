package com.dal;

import com.mysql.jdbc.Driver;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen
 */
public class ConnectSQL {

    private  ResultSet rs = null;
    private  Statement st = null;
    
    public ConnectSQL() {
    }

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
//            JOptionPane.showMessageDialog(null, "Kết nối CSDL Thành Công !!!");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kết nối CSDL thất bại !!!");
            return null;
        }

    }

    public Statement getStatement() throws SQLException{
       if(this.st==null?true:this.st.isClosed()){
           this.st = (Statement) this.getConnection().createStatement();
       }
        return st;
    }
    // ham thuc thi cau lenh Select lay du lieu tu CSDL
    public ResultSet excuteQuery(String query){
       
        Connection con;
        try {
            con = getConnection();
            
            st = (Statement) con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    public String excuteQuery1(String query){
       
        Connection con;
        try {
            con = getConnection();
            
            st = (Statement) con.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()) return rs.getString("tt");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    // ham thuc thi cau lenh khac ngoai Select
    public int executeUpdate(String query) throws SQLException{
        int res = Integer.MIN_VALUE;
        res = getStatement().executeUpdate(query);
        return res;
    }
    
    
    
}
