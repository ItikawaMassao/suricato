<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/assets/css/cadastro.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/time/integrante.css'/>">
		<title>Novo time</title>
	</head>
	<body>
		<%@include file="/WEB-INF/views/header.jsp" %>
		<div class="corpo">
			<h2 class="corpo-descricao">Criar time</h2>
			<form action="<c:url value='/time/criar'/>" class="cadastro" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type='hidden' name='integrantes[0].id' value="${usuario.id}" class="cadastro-campo_id"/>
				<input type='hidden' name='integrantes[0].nome' value="${usuario.nome}" class="cadastro-campo_nome"/>
				<label class="cadastro-descricao" for="nome">
					Time <form:errors path='time.nome' class="cadastro-erro"/>
				</label>
				<input type='text' name='nome' id="nome" class="cadastro-campo" value="${time.nome}" placeholder="Nome do time"/>
				<label class="cadastro-descricao" for="Participantes">
					Participantes <form:errors path='time.integrantes' class="cadastro-erro"/>
				</label>
				<label class="cadastro-campo --lista-integrantes" for="novoIntegrante">
					<span class="integrante">${usuario.nome}</span>
					<input type="text" class="integrante-adiciona" id="novoIntegrante">
				</label>
				<button type="submit" class="cadastro-botao">CRIAR</button>
			</form>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<script src="<c:url value='/assets/js/time/mostra.js'/>"></script>
	</body>
</html>