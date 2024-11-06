/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public Account validateAccount(String username, String password){
        Account kq=null;
        String sql = "select * from Account where username=? and password=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                kq = new Account(rs.getString(1), rs.getString(2));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return kq;
    }
    public boolean updatePassword(Account account){
       String sql = "update Account set password=? where username=?";
       conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getPassword());
            ps.setString(2, account.getUsername());
            
            int kq = ps.executeUpdate();
            if(kq > 0){
               return true;
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.toString());
        }
        return false;
    }
}
