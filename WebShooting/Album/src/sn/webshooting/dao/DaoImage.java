package sn.webshooting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sn.webshooting.entities.ImageAlbum;

public class DaoImage {
	private static final String INSERT_IMAGE_PREPARED = "INSERT INTO ws_images (titre,description,hauteur,largeur,url,date_creation,date_update,album)VALUES (?, ?, ?, ?,?,?,?,?)";
	private static final String SELECT_IMAGE_BY_ALBUM_PREPARED = "SELECT * FROM ws_images WHERE album = ?";
	private static final String SELECT_IMAGES_PUBLICS = "SELECT * FROM ws_images WHERE album IN (SELECT id from ws_album Where type='PUBLIC')";
	private static final String SELECT_IMAGE_BY_URL = "SELECT * from ws_images WHERE url = ? ";
	private static final String UPDATE_IMAGE_PREPARED = "UPDATE ws_images set titre = ?, description = ?,hauteur = ?, largeur = ?, url = ? , largeur = ?, date_creation = ? , date_update = ? ,album = ?   where id = ?";
	private static final String DELETE_IMAGE_PREPARED="delete from ws_images where id=?";
	
	private static final Connection connexion = ConnexionManager.getInstance();


	public static void ajouter(ImageAlbum imageAlbum) throws DaoException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(INSERT_IMAGE_PREPARED);
			preparedStatement.setString(1, imageAlbum.getTitre());
			preparedStatement.setString(2, imageAlbum.getDescription());
			preparedStatement.setInt(3, imageAlbum.getHauteur());
			preparedStatement.setInt(4, imageAlbum.getLargeur());
			preparedStatement.setString(5, imageAlbum.getUrl());
			preparedStatement.setString(6, imageAlbum.getDate_creation());
			preparedStatement.setString(7, imageAlbum.getDate_update());
			preparedStatement.setInt(8, imageAlbum.getAlbum());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de l'ajout", e);
		}
	}

	public static List<ImageAlbum> lister(int id) throws DaoException {
		List<ImageAlbum> images = null;
		try {
			String titre, description, url, date_creation, date_update;
			int hauteur, largeur, album;
			images = new ArrayList<ImageAlbum>();
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_IMAGE_BY_ALBUM_PREPARED);
			preparedStatement.setInt(1, id);
			ResultSet reponse = preparedStatement.executeQuery();

			while (reponse.next()) {
				titre = reponse.getString("titre");
				description = reponse.getString("description");
				hauteur = reponse.getInt("hauteur");
				largeur = reponse.getInt("largeur");
				url = reponse.getString("url");
				date_creation = reponse.getString("date_creation");
				date_update = reponse.getString("date_update");
				album = reponse.getInt("album");

				images.add(
						new ImageAlbum(titre, description, hauteur, largeur, url, date_creation, date_update, album));

			}
			return images;
		} catch (SQLException e) {
			throw new DaoException("Une erreur inattendue s'est produite", e);
		}
	}

	public static List<ImageAlbum> imagePublics() throws DaoException {
		List<ImageAlbum> images = null;
		try {
			String titre, description, url, date_creation, date_update;
			int hauteur, largeur, album;
			images = new ArrayList<ImageAlbum>();
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_IMAGES_PUBLICS);
			ResultSet reponse = preparedStatement.executeQuery();

			while (reponse.next()) {
				titre = reponse.getString("titre");
				description = reponse.getString("description");
				hauteur = reponse.getInt("hauteur");
				largeur = reponse.getInt("largeur");
				url = reponse.getString("url");
				date_creation = reponse.getString("date_creation");
				date_update = reponse.getString("date_update");
				album = reponse.getInt("album");

				images.add(
						new ImageAlbum(titre, description, hauteur, largeur, url, date_creation, date_update, album));

			}
			return images;
		} catch (SQLException e) {
			throw new DaoException("Une erreur inattendue s'est produite", e);
		}
	}
	public static ImageAlbum getImageByUrl(String url) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(SELECT_IMAGE_BY_URL);
			preparedStatement.setString(1, url);
			ResultSet resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				String titre = resultat.getString("titre");
				String description = resultat.getString("description");
				int hauteur = resultat.getInt("hauteur");
				int largeur = resultat.getInt("largeur");
				String date_creation = resultat.getString("date_creation");
				String date_update = resultat.getString("date_update");
				int album = resultat.getInt("album");
				return new ImageAlbum(titre, description, hauteur, largeur, url, date_creation, date_update, album);
			}
		} catch (SQLException e) {
			throw new DaoException("UtilisateurServlet Doesn't exist!", e);
		}
		return null;
	}
	public static void modifier(ImageAlbum album) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(UPDATE_IMAGE_PREPARED);
			preparedStatement.setString(1, album.getTitre());
			preparedStatement.setString(2, album.getDescription());
			preparedStatement.setInt(3, album.getHauteur());
			preparedStatement.setInt(4, album.getLargeur());
			preparedStatement.setString(5, album.getUrl());
			preparedStatement.setString(6, album.getDate_creation());
			preparedStatement.setString(7, album.getDate_update());
			preparedStatement.setInt(8, album.getAlbum());
			preparedStatement.setInt(5, album.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec Update", e);
		}		
	}
	public static void delete(int id) {
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(DELETE_IMAGE_PREPARED);
			
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			throw new DaoException("Echec d'ajout",e);
		
	}
	
}
	}
