package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingletonConnection;
import model.Produit;

public class GestionProduit {
	public int save(Produit p) {
		System.out.print(p);
		int i=0;
		java.sql.Connection conn=SingletonConnection.getConnection();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("INSERT INTO PRODUIT VALUES(?,?,?,?,?)");

		ps.setInt(1, p.getCode());
		
		ps.setDouble(2, p.getPrix());
		ps.setString(3, p.getDesignation());
		ps.setInt(4, p.getQte());
		ps.setInt(5, p.getId_category());
		 i=ps.executeUpdate();
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return i;
		}
	
	public boolean produitExists(int ref) {
	    java.sql.Connection conn = SingletonConnection.getConnection();
	    boolean exists = false;

	    try {
	        java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM produit WHERE code = ?");
	        ps.setInt(1, ref);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            exists = count > 0; 
	        }
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return exists;
	}

	
	public List<Produit> getProduits() {
		java.sql.Connection conn=SingletonConnection.getConnection();
		List<Produit> p =new ArrayList<Produit>();
		try {
		java.sql.PreparedStatement ps= conn.prepareStatement("select * from produit");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			p.add(new Produit(rs.getInt("code"),rs.getString("designation"),rs.getInt("qte"),rs.getFloat("prix"),rs.getInt("id_category")));
			}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return p;
	}
	public int updateProduit(Produit p) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		
		int i=-1;
		try {
		java.sql.PreparedStatement ps= conn.prepareStatement("UPDATE Produit SET designation=?,prix=?,qte=? WHERE code=?;");
		ps.setString(1, p.getDesignation());
		ps.setFloat(2, p.getPrix());
		ps.setInt(3, p.getQte());
		ps.setInt(4, p.getCode());
	     i=ps.executeUpdate();
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return i;
	}
	public int deleteProduit(int ref) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		try {
		java.sql.PreparedStatement ps= conn.prepareStatement("Delete from produit where code=?");
		ps.setInt(1,ref);
	    int i=ps.executeUpdate();
		ps.close();
		return i;
		} catch (SQLException e)
		{
		e.printStackTrace();
		return -1;
		}
		
	}
	public List<Produit> produitsParMC(String mc){
		java.sql.Connection conn=SingletonConnection.getConnection();
		System.out.print(conn);
		List<Produit> list=new ArrayList<>();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("select * from produit where designation like ? or code like ? or prix like ? or qte like ?");
		ps.setString(1,"%"+mc+"%");
		ps.setString(2,"%"+mc+"%");
		ps.setString(3,"%"+mc+"%");
		ps.setString(4,"%"+mc+"%");
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			list.add(new Produit(rs.getInt("code"),rs.getString("designation"),rs.getInt("qte"),rs.getFloat("prix"),rs.getInt("id_category")));
		}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return list;
		}
	public List<Produit> produitsParcategory(int id){
		java.sql.Connection conn=SingletonConnection.getConnection();
		System.out.print(conn);
		List<Produit> list=new ArrayList<>();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("select * from produit where id_category= ?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			list.add(new Produit(rs.getInt("code"),rs.getString("designation"),rs.getInt("qte"),rs.getFloat("prix"),rs.getInt("id_category")));
		}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return list;
		}
}
