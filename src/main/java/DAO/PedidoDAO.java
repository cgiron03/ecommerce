package DAO;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Pedido;
import utilidades.HibernateUtil;

public class PedidoDAO {

	public ArrayList<Pedido> listarPedido() {
		ArrayList<Pedido> clientes = new ArrayList<Pedido>();

		new HibernateUtil();
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			clientes = (ArrayList<Pedido>) session.createQuery("SELECT c FROM Pedido c", Pedido.class)
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