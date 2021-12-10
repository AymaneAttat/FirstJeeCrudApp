package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Produit;

public class ProduitDaoImpl implements IProduitDao {

	private SingletonConnection SingletonConn;

	public ProduitDaoImpl(SingletonConnection SingletonConn) {
        this.SingletonConn = SingletonConn;
    }
	@Override
	public Produit save(Produit p) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
		
		try {
			conn = SingletonConn.getConnection();
			preparedStatement = conn.prepareStatement("INSERT INTO product(nom,prix) VALUES(?,?)");
			preparedStatement.setString(1, p.getNomProduit());
            preparedStatement.setDouble(2, p.getPrix()); 
            preparedStatement.executeUpdate();//executeQuery executeUpdate
            conn.commit();
		}catch (SQLException e) {
			try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
            }
		}
		return p;
	}
	
	@Override
	public List<Produit> produitsParMC(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultat = null;
		try {
			conn = SingletonConn.getConnection();
			ps = conn.prepareStatement("select * from product where nom LIKE ? ;");
            ps.setString(1, "%"+mc+"%");
            resultat = ps.executeQuery();
            while (resultat.next()) {
                Long idProduit = resultat.getLong("id");
                String nomProduit = resultat.getString("nom");
                double prix = resultat.getDouble("prix");
                
                Produit p = new Produit();
                p.setIdProduit(idProduit);
                p.setNomProduit(nomProduit);
                p.setPrix(prix);
                prods.add(p);
            }
		}catch (SQLException e) {
			e.printStackTrace();
        }finally {
                try {
                    if (conn != null) {
                        conn.close();  
                    }
                } catch (SQLException e) {
                }
        }
		return prods;
	}
	
	@Override
	public List<Produit> lister(){
		List<Produit> prods = new ArrayList<Produit>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
			conn = SingletonConn.getConnection();
            statement = conn.createStatement();
            resultat = statement.executeQuery("select id,nom,prix from product;");
            while (resultat.next()) {
                Long idProduit = resultat.getLong("id");
                String nomProduit = resultat.getString("nom");
                double prix = resultat.getDouble("prix");
                
                Produit p = new Produit();
                p.setIdProduit(idProduit);
                p.setNomProduit(nomProduit);
                p.setPrix(prix);
                prods.add(p);
            }
		}catch (SQLException e) {
			e.printStackTrace();
        }finally {
                try {
                    if (conn != null) {
                        conn.close();  
                    }
                } catch (SQLException e) {
                }
        }
        return prods;
	}


	@Override
	public Produit getProduit(Long id) {
		Connection conn = null;
		ResultSet resultat = null;
		PreparedStatement ps = null;
		Produit p = new Produit();
		try {
			conn = SingletonConn.getConnection();
			ps = conn.prepareStatement("select * from product where id LIKE ? ;");
			ps.setLong(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				Long idProduit = resultat.getLong("id");
                String nomProduit = resultat.getString("nom");
                double prix = resultat.getDouble("prix");
                
                p.setIdProduit(idProduit);
                p.setNomProduit(nomProduit);
                p.setPrix(prix);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return p ;
	}

	@Override
	public Produit updateProduit(Produit p) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultat = null;
		try {
			conn = SingletonConn.getConnection();
			ps = conn.prepareStatement("update product set nom=? , prix=? where id=? ");
			ps.setString(1, p.getNomProduit());
			ps.setDouble(2, p.getPrix());
			ps.setLong(3, p.getIdProduit());
			ps.executeUpdate();
			conn.commit();
			//ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void deleteProduit(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SingletonConn.getConnection();
			ps = conn.prepareStatement("delete from product WHERE id = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			conn.commit();
			//ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
