package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingletonConnection;
import model.Categorie;
import model.Produit;

public class GestionCategorie {
	public int save(Categorie c) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		int i=0;
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("INSERT INTO categorie VALUES(?,?)");

		ps.setInt(1, c.getCodeCat());
		
		
		ps.setString(2, c.getDesignationCat());
		
		i=ps.executeUpdate();
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return i;
		}
	
	public Categorie getCategorie(int ref) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		Categorie c =new Categorie();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("select * from categorie where codeCat=?");
		ps.setInt(1,ref);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			c.setCodeCat(rs.getInt("codeCat"));
			c.setDesignationCat(rs.getString("designationCat"));
		}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return c;
	}
	
	
	public List<Categorie> getCategories() {
		java.sql.Connection conn=SingletonConnection.getConnection();
		List<Categorie> p =new ArrayList();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("select * from categorie");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			p.add(new Categorie(rs.getInt("codeCat"),rs.getString("designationCat")));
			}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return p;
	}
	public boolean categorieExists(int ref) {
	    java.sql.Connection conn = SingletonConnection.getConnection();
	    boolean exists = false;

	    try {
	        java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM categorie WHERE codeCat = ?");
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
	public int updateCategorie(Categorie c) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		
		int i=-1;
		try {
		java.sql.PreparedStatement ps= conn.prepareStatement("UPDATE Categorie SET designationCat=? WHERE codeCat=?;");
		ps.setString(1,c.getDesignationCat());
		ps.setInt(2, c.getCodeCat());
	     i=ps.executeUpdate();
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return i;
	}
	public int deleteCategorie(int ref) {
		java.sql.Connection conn=SingletonConnection.getConnection();
		try {
		java.sql.PreparedStatement ps= conn.prepareStatement("Delete from Categorie where codeCat=?");
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
	public List<Categorie> CategorieParMC(String mc){
		java.sql.Connection conn=SingletonConnection.getConnection();
	
		List<Categorie> list=new ArrayList<>();
		try {

		java.sql.PreparedStatement ps= conn.prepareStatement("select * from Categorie where designationCat like ? or codeCat like ?");
		ps.setString(1,"%"+mc+"%");
		ps.setString(2,"%"+mc+"%");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			list.add(new Categorie(rs.getInt("codeCat"),rs.getString("designationCat")));
		}
		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		}
		return list;
		}

}
