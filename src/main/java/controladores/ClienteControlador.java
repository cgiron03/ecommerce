package controladores;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Cliente;

import java.io.IOException;
import java.util.ArrayList;

import DAO.ClienteDAO;

@jakarta.servlet.annotation.WebServlet(name = "ClienteControlador", urlPatterns = { "/ClienteControlador" })
public class ClienteControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteDAO cDao = new ClienteDAO();
	RequestDispatcher rd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cliente usuario = (Cliente) session.getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect(request.getContextPath());

		}

		String action = request.getParameter("action");

		if (action.equals("eliminar")) {
			eliminar(request, response);
		}

		if (action.equals("listar")) {
			listar(request, response);
		}

		if (action.equals("obtenerUsuario")) {
			obtenerUsuario(request, response);
		}

		if (action.equals("salir")) {
			cerrarSesion(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("login")) {
			login(request, response);
		}

		if (action.equals("listar")) {
			buscar(request, response);
		}

		if (action.equals("actualizar")) {
			actualizarUsuario(request, response);
		}

		if (action.equals("crearusuario")) {
			crearUsuario(request, response);
		}

	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");

		Cliente logueado = cDao.login(usuario, password);

		if (logueado != null) {

			HttpSession session = request.getSession();
			session.setAttribute("usuario", logueado);
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);

		} else {

			String msg = "usuario y/o password son incorrectos";
			request.setAttribute("message", msg);
			rd = request.getRequestDispatcher("/listarproductos.jsp");
			rd.forward(request, response);

		}

	}

	protected void buscar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String queryParametro = request.getParameter("query");
		ArrayList<Cliente> usuarios = cDao.buscarUsuarios(queryParametro);
		System.out.println(usuarios);
		request.setAttribute("usuarios", usuarios);
		rd = request.getRequestDispatcher("/listar.jsp");
		rd.forward(request, response);
	}

protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int usuarioId = Integer.parseInt(request.getParameter("id")) ;
		boolean usuarioEliminado = cDao.eliminar(usuarioId);
		request.setAttribute("usuarios", usuarioEliminado);
		rd =  request.getRequestDispatcher("/ClienteControlador?action=listar");
		rd.forward(request, response);
		
	} 

	protected void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Cliente> cliente = cDao.listarClientes();
		request.setAttribute("clientes", cliente);
		System.out.println(cliente);
		rd = request.getRequestDispatcher("/mostrarclientes.jsp");
		rd.forward(request, response);

	}

	protected void obtenerUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuarioId = Integer.parseInt(request.getParameter("id"));
		Cliente usuario = cDao.obtenerUsuario(usuarioId);
		request.setAttribute("usuario", usuario);
		rd = request.getRequestDispatcher("/editar.jsp");
		rd.forward(request, response);

	}

	protected void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuarioId = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String correo = request.getParameter("correo");

		boolean usuarioActualizado = cDao.actualizar(usuarioId, nombre, apellido, usuario, password, correo);

		if (usuarioActualizado) {
			listar(request, response);
		}
	}

	protected void crearUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String correo = request.getParameter("correo");

		boolean usuarioActualizado = cDao.crear(id, nombre, apellido, usuario, password, correo);
		rd = request.getRequestDispatcher("/mostrarclientes.jsp");
		rd.forward(request, response);

		if (usuarioActualizado) {
			listar(request, response);
		}
	}

	protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

}