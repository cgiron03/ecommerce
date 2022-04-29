package DAO;
import model.Cliente;
import java.util.ArrayList;
import java.util.List;

import utilidades.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;



public class ClienteDAO {
	
	public ArrayList<Cliente> listarClientes() {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			clientes = (ArrayList<Cliente>) session.createQuery("SELECT u FROM Cliente u", Cliente.class).getResultList();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(tr!= null) {
				tr.rollback();
			}
			
			clientes = null;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
		
		return clientes;
	}
	
	public Cliente login(String username, String password) {
		
		
		Cliente cliente = null;
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			Query<Cliente> user = (Query<Cliente>) session.createQuery("Select u from cliente u where u.usuario='"+username+"' and u.passw='"+password+"'",  Cliente.class);
			List<Cliente> logUser = user.list();
			
			if(logUser.size() > 0 ) {
				cliente = logUser.get(0);
				
				return cliente;	
			} else {
				return null;
			}
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			System.out.print(ex);
			
			if(tr!= null) {
				tr.rollback();
			}
			
			return null;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
		
	}
	
	
	public ArrayList<Cliente> buscarUsuarios(String filter) {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			if(filter != null ){
				String sql = "Select u from Usuario u ";
				sql+="where usuario like'%"+filter+"%'";
				clientes = (ArrayList<Cliente>) session.createQuery(sql, Cliente.class).getResultList();
			}
			
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(tr!= null) {
				tr.rollback();
			}
			
			clientes = null;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
		
		return clientes;
	}

	
	public boolean eliminar(int usuarioId) {
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		try {
			
			tr = session.beginTransaction();
			
			Cliente usuario = session.get(Cliente.class, usuarioId);
			session.delete(usuario);
			
			tr.commit();
			
			return true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(tr!= null) {
				tr.rollback();
			}
			
			return false;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
	}
	
	
	public Cliente obtenerUsuario(int usuarioId) {
		
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			Cliente usuario = session.get(Cliente.class, usuarioId);
			return usuario;
			
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			System.out.print(ex);
			
			if(tr!= null) {
				tr.rollback();
			}
			
			return null;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
		
	}
	
	
	public boolean actualizar(int usuarioId, String nombre, String apellido, String user, String pass,String correo ) {
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		try {
			
			tr = session.beginTransaction();
			
			Cliente clienteActualizar = session.get(Cliente.class, usuarioId);
			clienteActualizar.setNombre(nombre);
			clienteActualizar.setApellido(apellido);
			clienteActualizar.setUsuario(user);
			clienteActualizar.setPassw(pass);
			clienteActualizar.setCorreo(correo);
			
			session.update(clienteActualizar);
			
			tr.commit();
			
			return true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(tr!= null) {
				tr.rollback();
			}
			
			return false;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
	}
	
	public boolean crear(int id, String nombre, String apellido, String user, String pass, String correo ) {
		
		SessionFactory sessionF = HibernateUtil.getSessionFactory();
		Session session = sessionF.getCurrentSession();
		
		Transaction tr = null;
		try {
			
			tr = session.beginTransaction();
			
			Cliente clienteCrear = new Cliente() ;
			clienteCrear.setClienteId(id);
			clienteCrear.setNombre(nombre);
			clienteCrear.setApellido(apellido);
			clienteCrear.setUsuario(user);
			clienteCrear.setPassw(pass);
			clienteCrear.setCorreo(correo);
			session.save(clienteCrear);
			
			tr.commit();
			
			return true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(tr!= null) {
				tr.rollback();
			}
			
			return false;
		}
		
		finally {
			session.close();
			sessionF.close();
		}
		
	}
	
	
	
}
 
