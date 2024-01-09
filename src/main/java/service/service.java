package service;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dao.GestionProduit;
import metier.metierCategorie;
import metier.metierProduit;
import model.Categorie;
import model.Produit;
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class service {
metierCategorie mc;
metierProduit mp;
public service() {
	super();
	mp=new metierProduit();
	mc=new metierCategorie();
}
@POST
@Path("/addproduit")
public Response ajouterProduit(Produit p) {
    int resultat= mp.ajouter(p);
    if(resultat==1) {
    	return Response.status(Response.Status.OK)
                .entity("add with succes")
                .build();
    }else {
    	return Response.status(Response.Status.BAD_REQUEST)
                .entity("Product exist")
                .build();
    }
}
@GET
@Path("/produit/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response exist(@PathParam("id") int id) {
    boolean doesExist = mp.exist(id);

    if (doesExist) {
        
        return Response.status(Response.Status.OK)
                       .entity("Product exists")
                       .build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Product does not exist")
                       .build();
    }
    }

@PUT
@Path("/updateproduit")
public Response updateProduit(Produit p) {
	int resultat= mp.Update(p);
    if(resultat==1) {
    	return Response.status(Response.Status.OK)
                .entity("update with succes")
                .build();
    }else {
    	return Response.status(Response.Status.BAD_REQUEST)
                .entity("Product n'exist pas")
                .build();
    }
}
@DELETE
@Path("/delete/{id}")
public Response delete(@PathParam("id") int id) {
	int response=mp.delete(id);
if (response==1) {
        
        return Response.status(Response.Status.OK)
                       .entity("delete with succes")
                       .build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Product does not exist")
                       .build();
    }
}
@GET
@Path("/search/{mc}")
public List<Produit> search(@PathParam("mc") String mc){
	return mp.search(mc);
}
@POST
@Path("/addcategorie")
public Response ajouterCategorie(Categorie c) {
	int resultat=mc.ajouter(c);
	if(resultat==1) {
    	return Response.status(Response.Status.OK)
                .entity("add with succes")
                .build();
    }else {
    	return Response.status(Response.Status.BAD_REQUEST)
                .entity("Product exist")
                .build();
    }
}
@GET
@Path("/categorie/{id}")
public Categorie getCategorie(@PathParam("id") int ref) {
	this.mc=new metierCategorie();
	return mc.getCategorie(ref);
}
@PUT
@Path("/updatecategorie")
public Response updateCategorie(Categorie c) {
	int resultat= mc.Update(c);
    if(resultat==1) {
    	return Response.status(Response.Status.OK)
                .entity("update with succes")
                .build();
    }else {
    	return Response.status(Response.Status.BAD_REQUEST)
                .entity("categorie n'existe pas")
                .build();
    }	
}
@DELETE
@Path("/deleteCategorie/{id}")
public Response deleteCategorie(@PathParam("id") int id) {
	int response=mc.delete(id);
if (response==1) {
        
        return Response.status(Response.Status.OK)
                       .entity("delete with succes")
                       .build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Categorie does not exist")
                       .build();
    }
}
@GET
@Path("/existCategorie/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response existCategorie(@PathParam("id") int id) {
    boolean doesExist = mc.exist(id);
System.out.print(doesExist);
    if (doesExist) {
        
        return Response.status(Response.Status.OK)
                       .entity("categorie exists")
                       .build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("categorie does not exist")
                       .build();
    }
    }
@GET
@Path("/searchCategorie/{m}")
public List<Categorie> searchCategorie(@PathParam("m") String m){
	return mc.search(m);
}
@GET()
@Path("/categories")
public List<Categorie> listerCategorie(){
	return mc.lister();
}
@GET()
@Path("/produits")
public List<Produit> lister(){
	metierProduit mp =new metierProduit();
	return mp.lister();
}
@GET
@Path("/listerParCategory/{id}")
public List<Produit> listerParCategory(@PathParam("id") int id){
	return mp.listerParCategory(id);
}
}
