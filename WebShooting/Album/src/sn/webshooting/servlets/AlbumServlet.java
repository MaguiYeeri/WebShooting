package sn.webshooting.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.webshooting.dao.DaoAlbum;
import sn.webshooting.dao.DaoImage;

@WebServlet({ "/album/details", "/album/create" })
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DETAIL = "/WEB-INF/album/details.jsp";

	public AlbumServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		if (requestedUrl.equals("/album/details")) {
			request.setAttribute("albumDetails", DaoAlbum.getAlbumById(Integer.parseInt(request.getParameter("album"))));
			request.setAttribute("imagesPublics", DaoImage.lister(Integer.parseInt(request.getParameter("album"))));
			getServletContext().getRequestDispatcher(VUE_DETAIL).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
