package metier;

import java.util.List;
import dao.GestionProduit;
import model.Produit;

public class metierProduit {
    GestionProduit gp;
    public metierProduit() {
        super();
        gp = new GestionProduit();
    }
    public int ajouter(Produit p) {
        return gp.save(p);
    }
    public boolean exist(Integer ref) {
        return gp.produitExists(ref);
    }
    public List<Produit> lister() {
    	//gp=new GestionProduit();
        return gp.getProduits();
    }
    public List<Produit> listerParCategory(int id) {
    	//gp=new GestionProduit();
        return gp.produitsParcategory(id);
    }
    public int Update(Produit p) {
    	return gp.updateProduit(p);
    }
    public int delete(int ref) {
    	return gp.deleteProduit(ref);
    }
    public List<Produit> search(String mc){
    	return gp.produitsParMC(mc);
    }
}
