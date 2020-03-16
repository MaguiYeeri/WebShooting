package sn.webshooting.servlets;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import sn.webshooting.dao.DaoAlbum;
import sn.webshooting.dao.DaoImage;
import sn.webshooting.dao.DaoUtilisateur;
import sn.webshooting.entities.Album;
import sn.webshooting.entities.FormErreur;
import sn.webshooting.entities.ImageAlbum;
import sn.webshooting.entities.Utilisateur;
import sn.webshooting.enumeration.AlbumTheme;
import sn.webshooting.enumeration.AlbumType;

/**
 * Servlet implementation class UtilisateurServlet
 */
@WebServlet({ "/user/create", "/user/connect", "/user", "/user/albums", "/user/account", "/user/album/voir",
		"/user/album/modifier","/user/album/supprimer", "/user/albumsShared", "/user/albumsShared/voir","/user/album/images/modifier","/user/album/images/supprimer" })
@MultipartConfig
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN = "/WEB-INF/user/login.jsp";
	private static final String VUE_SIGNIN = "/WEB-INF/user/signin.jsp";
	private static final String VUE_ACCUEIL = "/WEB-INF/user/accueil.jsp";
	private static final String VUE_ALBUMS = "/WEB-INF/user/albums.jsp";
	private static final String VUE_ALBUMS_SHARED = "/WEB-INF/user/albumsShared.jsp";
	private static final String VUE_IMAGES = "/WEB-INF/user/images.jsp";
	private static final String VUE_COMPTE = "/WEB-INF/user/compte.jsp";
	private static final String VUE_IMAGES_SHARED = "/WEB-INF/user/imagesshared.jsp";
	private static final String VUE_MODIFIER_ALBUM = "/WEB-INF/user/modifierAlbum.jsp";
	private static final String VUE_MODIFIER_IMAGES = "/WEB-INF/user/modifierImage.jsp";

	private static final String ATT_SESSION_USER = "sessionUtilisateur";
	private static final String ATT_FORM_ERROR = "formError";
	private static final String SAVE_DIR = "E:\\presentation\\Album\\WebContent\\imagesuploaded";

	private FormErreur error;
	private HttpSession session;
	private Utilisateur utilisateur;

	public UtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		if (requestedUrl.equals("/user/create")) {
			getServletContext().getRequestDispatcher(VUE_SIGNIN).forward(request, response);
		} else if (requestedUrl.equals("/user/connect")) {
			request.setAttribute(ATT_FORM_ERROR, error);
			getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
		} else if (requestedUrl.equals("/user")) {
			request.setAttribute("imagesPublics", DaoImage.imagePublics());
			request.setAttribute("urlImage", SAVE_DIR);
			getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(request, response);
		} else if (requestedUrl.equals("/user/albums")) {
			if (session != null) {
				String login = utilisateur.getLogin();
				request.setAttribute("albums", DaoAlbum.listAlbumByUser(login));
				getServletContext().getRequestDispatcher(VUE_ALBUMS).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/user/connect");
			}
		
		}else if (requestedUrl.equals("/user/albumsShared")) {
			if (session != null) {
				String login = utilisateur.getLogin();
				request.setAttribute("albums", DaoAlbum.listAlbumSharedByUser(login));
				getServletContext().getRequestDispatcher(VUE_ALBUMS_SHARED).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/user/connect");
			}
		
		} else if (requestedUrl.equals("/user/album/voir")) {
			request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("urlImage", SAVE_DIR);
			getServletContext().getRequestDispatcher(VUE_IMAGES).forward(request, response);
		} 
		 else if (requestedUrl.equals("/user/albumsShared/voir")) {
				request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("urlImage", SAVE_DIR);
				getServletContext().getRequestDispatcher(VUE_IMAGES_SHARED).forward(request, response);
			} 
		 else if (requestedUrl.equals("/user/account")) {
			request.setAttribute("infoUtilisateur", DaoUtilisateur.getUserByLogin(request.getParameter("login")));
			getServletContext().getRequestDispatcher(VUE_COMPTE).forward(request, response);
		}
		 else if (requestedUrl.equals("/user/album/modifier")) {
				request.setAttribute("infoAlbum", DaoAlbum.getAlbumById(Integer.parseInt(request.getParameter("id"))));
				getServletContext().getRequestDispatcher(VUE_MODIFIER_ALBUM).forward(request, response);
			}
		 else if (requestedUrl.equals("/user/album/images/modifier")) {
				request.setAttribute("infoAlbum", DaoImage.getImageByUrl(request.getParameter("url")));
				getServletContext().getRequestDispatcher(VUE_MODIFIER_IMAGES).forward(request, response);
			}
		 else if(requestedUrl.equals("/user/album/images/supprimer"))
			{
				int id= Integer.parseInt(request.getParameter("id"));
				DaoImage.delete(id);
				getServletContext().getRequestDispatcher(VUE_IMAGES).forward(request, response);
			}
		 else if(requestedUrl.equals("/user/album/supprimer"))
			{
				int id= Integer.parseInt(request.getParameter("id"));
				DaoAlbum.delete(id);
				getServletContext().getRequestDispatcher(VUE_ALBUMS).forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		if (requestedUrl.equals("/user/connect")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			if (DaoUtilisateur.seConnecter(login, password)) {
				utilisateur = DaoUtilisateur.getUserByLogin(login);
				session = request.getSession();
				session.setAttribute(ATT_SESSION_USER, utilisateur);
				response.sendRedirect(request.getContextPath() + "/user");

			} else {
				error = new FormErreur("Login ou mot de pass incorrect!");
				response.sendRedirect(request.getContextPath() + "/user/connect");
			}
		} else if (requestedUrl.equals("/user/create")) {
			request.setCharacterEncoding("utf-8");

			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			Utilisateur Utilisateur = new Utilisateur(nom, prenom, login, password);
			DaoUtilisateur.ajouter(Utilisateur);
			response.sendRedirect(request.getContextPath() + "/accueil");
		} else if (requestedUrl.equals("/user/albums")) {
			request.setCharacterEncoding("utf-8");

			String nom = request.getParameter("nomAlbum");
			AlbumType type = AlbumType.valueOf(request.getParameter("type"));
			AlbumTheme theme = AlbumTheme.valueOf(request.getParameter("theme"));
			String proprietaire = utilisateur.getLogin();

			Album album = new Album(0, nom, type, theme, proprietaire);
			DaoAlbum.ajouter(album);
			request.setAttribute("albums", DaoAlbum.listAlbumByUser(proprietaire));
			getServletContext().getRequestDispatcher(VUE_ALBUMS).forward(request, response);
		} else if (requestedUrl.equals("/user/album/voir")) {
			request.setCharacterEncoding("utf-8");
			// Copier le fichier uploader
			Part part = request.getPart("url");
			String url = part.getSubmittedFileName();
			InputStream input = part.getInputStream();
			Path target = Paths.get(SAVE_DIR + url);
			Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
			Image img = ImageIO.read(new File(SAVE_DIR + url));

			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			int hauteur = img.getHeight(null);
			int largeur = img.getWidth(null);
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dat = new Date();
			String date_creation = format.format(dat);
			String date_update = format.format(dat);
			int album = Integer.parseInt(request.getParameter("id"));
			ImageAlbum imageAlbum = new ImageAlbum(titre, description, hauteur, largeur, url, date_creation,
					date_update, album);
			DaoImage.ajouter(imageAlbum);
			request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("urlImage", SAVE_DIR);
			getServletContext().getRequestDispatcher(VUE_IMAGES).forward(request, response);
		}
		else if (requestedUrl.equals("/user/albumsShared")) {
			request.setCharacterEncoding("utf-8");

			int id = Integer.parseInt(request.getParameter("id"));
			String proprietaire = utilisateur.getLogin();
			request.setAttribute("albums", DaoAlbum.listAlbumByUser(proprietaire));
			getServletContext().getRequestDispatcher(VUE_ALBUMS_SHARED).forward(request, response);
		} else if (requestedUrl.equals("/user/albumShared/voir")) {
			request.setCharacterEncoding("utf-8");
			// Copier le fichier uploader
			Part part = request.getPart("url");
			String url = part.getSubmittedFileName();
			InputStream input = part.getInputStream();
			Path target = Paths.get(SAVE_DIR + url);
			Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
			Image img = ImageIO.read(new File(SAVE_DIR + url));

			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			int hauteur = img.getHeight(null);
			int largeur = img.getWidth(null);
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dat = new Date();
			String date_creation = format.format(dat);
			String date_update = format.format(dat);
			int album = Integer.parseInt(request.getParameter("id"));
			ImageAlbum imageAlbum = new ImageAlbum(titre, description, hauteur, largeur, url, date_creation,
					date_update, album);
			DaoImage.ajouter(imageAlbum);
			request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("urlImage", SAVE_DIR);
			getServletContext().getRequestDispatcher(VUE_IMAGES_SHARED).forward(request, response);
		}
		else if (requestedUrl.equals("/user/account")) {
			request.setCharacterEncoding("utf-8");

			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			Utilisateur Utilisateur = new Utilisateur(nom, prenom, login, password);
			DaoUtilisateur.modifier(Utilisateur);
			response.sendRedirect(request.getContextPath() + "/user/connect");

		}
		else if (requestedUrl.equals("/user/album/modifier")) {
			request.setCharacterEncoding("utf-8");

			String nom = request.getParameter("nomAlbum");
			AlbumType type = AlbumType.valueOf(request.getParameter("type"));
			AlbumTheme theme = AlbumTheme.valueOf(request.getParameter("theme"));
			String proprietaire = utilisateur.getLogin();

			Album album = new Album(Integer.parseInt(request.getParameter("id")), nom, type, theme, proprietaire);
			DaoAlbum.modifier(album);
			request.setAttribute("albums", DaoAlbum.listAlbumByUser(proprietaire));
			response.sendRedirect(request.getContextPath() + "/user/albums");
		}else if (requestedUrl.equals("/user/album/images/modifier")) {
			request.setCharacterEncoding("utf-8");
			Part part = request.getPart("url");
			String url = part.getSubmittedFileName();
			InputStream input = part.getInputStream();
			Path target = Paths.get(SAVE_DIR + url);
			Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
			Image img = ImageIO.read(new File(SAVE_DIR + url));

			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			int hauteur = img.getHeight(null);
			int largeur = img.getWidth(null);
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dat = new Date();
			String date_creation = format.format(dat);
			String date_update = format.format(dat);
			int album = Integer.parseInt(request.getParameter("id"));
			ImageAlbum image = new ImageAlbum(titre, description, hauteur, largeur, url, date_creation,
					date_update, album);
			DaoImage.modifier(image);
			request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("urlImage", SAVE_DIR);
			getServletContext().getRequestDispatcher(VUE_IMAGES).forward(request, response);
		}
	}

}
