package metier;

import java.util.List;

import dao.GestionCategorie;
import dao.GestionProduit;
import model.Categorie;
import model.Produit;

public class metierCategorie {
	GestionCategorie gc;
	public metierCategorie() {
		super();
		gc=new GestionCategorie();
		
	}
	public int ajouter(Categorie c) {
		return gc.save(c);
	}
	public Categorie getCategorie(int ref) {
		return gc.getCategorie(ref);
	}
	public List<Categorie> search(String mc){
    	return gc.CategorieParMC(mc);
    }
	public int Update(Categorie c) {
    	return gc.updateCategorie(c);
    }
    public int delete(int ref) {
    	return gc.deleteCategorie(ref);
    }
    public boolean exist(Integer ref) {
        return gc.categorieExists(ref);
    }
    public List<Categorie> lister() {
    	
        return gc.getCategories();
    }
}
