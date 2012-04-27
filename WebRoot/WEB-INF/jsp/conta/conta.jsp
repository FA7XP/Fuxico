<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/conta.css" />
		<title>Fuxico Conta</title>
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js"></script>
		<script src="${contextPath}/js/md5.js"></script>
		<script>

$(function() {

	$("#formConta").validate( {
		rules : {
			"usuario.login" : {
				required : true
			},
			"usuario.nome" : {
				required : true
			},
			"usuario.senha" : {
				required : true
			},
			"usuario.email" : {
				required : true
			}
		},
		messages : {
			"usuario.login" : {
				required : "Login não pode ser em branco."
			},
			"usuario.nome" : {
				required : "Nome não pode ser em branco."
			},
			"usuario.senha" : {
				required : "Senha não pode ser em branco."
			},
			"usuario.email" : {
				required : "Email não pode ser em branco."
			}
		}
	});
});
</script>
	</head>
	<body background="../../../imagens/wallpaper.jpg">
	<h1> CRIAR CONTA </h1>
	<div id="erro">${erro}</div>
		<div id="contaGeral">
			<div id="conta">
				<form method="post" id="formConta" action="/fuxico/conta">
					<div class="clearfix">
						<label>
							Login:
						</label>
						<div class="input">
							<input type="text" maxlength="32" name="usuario.login">
							<br>
						</div>
					</div>
					<div class="clearfix">
						<label>
							Senha:
						</label>
						<div class="input">
							<input type="password" maxlength="32" name="usuario.senha">
						</div>
					</div>
					<div class="clearfix">
						<label>
							Nome Usuario:
						</label>
						<div class="input">
							<input class="nome" type="text" maxlength="255"
								name="usuario.nome">
						</div>
					</div>
					<div class="clearfix">
						<label>
							Email:
						</label>
						<div class="input">
							<input class="email" type="text" maxlength="64"
								name="usuario.email">
						</div>
					</div>
					<br>
					<button type="submit" id="criar" class="btn small">
						Criar Conta
					</button>
				</form>
			</div>
		</div>
	</body>
</html>