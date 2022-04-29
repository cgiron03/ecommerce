
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>
<%@ page import="DAO.*"%>
<%@ page import="controladores.*"%>
<%@ page import="java.util.List"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Lista Clientes</title>
</head>
<body style="background-color: black">
	<h1 style="text-align: center" class=" text-white">Lista Clientes</h1>
	<div class="container">
		<table class="table">
			<thead>
				<tr class="text-danger">
					<th scope="col">#</th>
					<th scope="col">Nombre</th>
					<th scope="col">Usuario</th>
					<th scope="col">Correo</th>
				</tr>
			</thead>
			<tbody>

				<%
				ClienteDAO cDAO = new ClienteDAO();
				List<Cliente> clientes = cDAO.listarClientes();
				for (Cliente cliente : clientes) {
					out.print("<tr class= 'text-white'><td>" + cliente.getClienteId() + "</td>" + "<td>" + cliente.getNombre() + "</td>"
					+ "<td>" + cliente.getUsuario() + "</td>" + "<td>" + cliente.getCorreo() + "</td>"
					+ "<td><button class='btn btn-danger'>Editar</button> </td>" + "<td>" + "</td>"
					+ "<td><a href = 'ClienteControlador?action=eliminar&id="+cliente.getClienteId()+"' class='btn btn-danger'>Eliminar</a> </td>" + "" + "<td>" + "</td></tr>");

				}
				%>
			</tbody>
		</table>

		<button type="button" class="btn btn-danger" data-bs-toggle="modal"
			data-bs-target="#exampleModal">Agregar Cliente</button>
	</div>

	<!-- Button trigger modal -->


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true" style ="background-color:black">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Agregar Usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form method="post" action ="ClienteControlador">

						<input type="text" class='form-control my-2' placeholder="id" name = "id">
						<input type="text" class='form-control my-2' placeholder="usuario" name = "usuario">
						<input type="text" class='form-control my-2' placeholder="nombre" name = "nombre">
						<input type="text" class='form-control my-2'placeholder="apellido" name = "apellido">
						 <input type="text" class='form-control my-2' placeholder="password" name = "password"> 
						 <input type="text" class='form-control my-2' placeholder="correo" name = "correo">
						 <input type= "hidden" name="action" value = "crearusuario">
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>
							<button type="submit" class="btn btn-primary">Guardar</button>
						</div>

					</form>
				</div>

			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous"></script>
</body>
</html>
</head>




