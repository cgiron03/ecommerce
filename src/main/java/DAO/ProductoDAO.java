package DAO;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Producto;
import utilidades.HibernateUtil;

public class ProductoDAO {

	public ArrayList<Producto> listarPedido() {
		ArrayList<Producto> clientes = new ArrayList<Producto>();

		new HibernateUtil();
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			clientes = (ArrayList<Producto>) session.createQuery("SELECT c FROM Producto c", Producto.class)
					.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			clientes = null;
		} finally {
			session.close();
			sessionF.close();
		}
		return clientes;
	}

}