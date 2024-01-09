package test;

import connection.SingletonConnection;
import dao.GestionCategorie;
import dao.GestionProduit;
import metier.metierCategorie;
import metier.metierProduit;
import model.Categorie;
import model.Produit;

public class Test {
	public static void main(String[] args) {
		SingletonConnection s=new SingletonConnection();
		//System.out.print(s);
		//Produit p=new Produit(6,"pc",3,3000f);
		//GestionProduit gp=new GestionProduit();
		GestionCategorie gc=new GestionCategorie();
		metierCategorie mp=new metierCategorie();
		metierProduit mc=new metierProduit();
		
		//gc.save(new Categorie(2,"informatique"));
		//System.out.print(mp.ajouter(p));
		System.out.print(mc.listerParCategory(2));
		//mp.ajouter(p);
		//System.out.print(mp.getProduit(8));
		//mp.Update(p);
	}
}
