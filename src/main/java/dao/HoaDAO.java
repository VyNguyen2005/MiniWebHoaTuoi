/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Hoa;

/**
 *
 * @author Administrator
 */
public class HoaDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Hoa> getTop10() {
        ArrayList<Hoa> ds = new ArrayList<>();
        String sql = "select top 10 * from Hoa order by gia desc";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new Hoa(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }

    //Phương thức đọc hoa theo thể loại
    public ArrayList<Hoa> getByCategoryId(int maloai) {
        ArrayList<Hoa> ds = new ArrayList<>();
        String sql = "select * from Hoa where maloai=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maloai);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new Hoa(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }

    public ArrayList<Hoa> getAll() {
        ArrayList<Hoa> dsHoa = new ArrayList<>();
        String sql = "select * from Hoa";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dsHoa.add(new Hoa(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.toString());
        }
        return dsHoa;
    }

    public boolean Insert(Hoa hoa) {
        String sql = "insert into hoa (tenhoa,gia,hinh,maloai,ngaycapnhat) values (?,?,?,?,?)";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hoa.getTenhoa());
            ps.setDouble(2, hoa.getGia());
            ps.setString(3, hoa.getHinh());
            ps.setInt(4, hoa.getMaloai());
            ps.setDate(5, hoa.getNgaycapnhat());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }

    public boolean Update(Hoa hoa) {
        String sql = "update hoa set tenhoa=?,gia=?,hinh=?,maloai=?,ngaycapnhat=? where mahoa=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hoa.getTenhoa());
            ps.setDouble(2, hoa.getGia());
            ps.setString(3, hoa.getHinh());
            ps.setInt(4, hoa.getMaloai());
            ps.setDate(5, hoa.getNgaycapnhat());
            ps.setInt(6, hoa.getMahoa());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }

    public boolean Delete(int mahoa) {
        String sql = "delete from hoa where mahoa=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, mahoa);
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }

    public int getNextMahoa() {
        int maHoa = 0;
        String sql = "select max(mahoa) as maxMa from hoa";

        try {
            conn = DbContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maHoa = rs.getInt("maxMa");
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.toString());
        }

        return maHoa + 1;
    }

    public static void main(String[] args) {
        HoaDAO hoaDao = new HoaDAO();
        ArrayList<Hoa> dsHoa = hoaDao.getTop10();
        for (Hoa hoa : dsHoa) {
            System.out.println(hoa);
        }

        dsHoa = hoaDao.getByCategoryId(2);
        for (Hoa hoa : dsHoa) {
            System.out.println(hoa);
        }
    }
}
