<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<link rel="shortcut icon" href="${contextPath}/imagens/icones/fuxico.ico" type="image/x-icon" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Fuxico</title>
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js"></script>		
		<script src="${contextPath}/js/md5.js"></script>
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		<script>
			function criptografarSenha() {
				var usuarioPassword = $("#uruarioPassword").val();
				$("#senhaCriptografada").val(hex_md5(usuarioPassword));
			};
			$(function() {
				$("#usuarioLogin").focus();
				$("#formLogin").validate({
					rules : {
						"nome" : { required : true },
						"senha" : { required : true }
					},
					messages : {
						"nome" : { required : "Digite o nome." },
						"senha" : { required : "Digite a senha." }
					}
				});
				$("#logar").click(function() {
					if( $("#formLogin").valid() ) $("#formLogin").submit();
				});
				$("#formLogin").find("input").keydown(function(event) {
					if( event.keyCode === 13 ) $("#logar").click(); 
				});
			});
		</script>
	</head>
	<body>
		<div id="geral">
			<div id="login">
				<div id="fuxico"></div>
				<div id="logoLogin">
				    <form method="post" id="formLogin" onsubmit="/fuxico/login" action="/fuxico/login">
				    	<input type="hidden" id="senhaCriptografada" name="senhaCriptografada" />
				    	<div id="erro">${erro}</div>
				    	<div class="clearfix">
					    	<label>Login:</label>
					    	<div class="input"><input type="text" maxlength="32" class="span2-5" name="nome" id="usuario.login"></div>
				    	</div>
				    	<div class="clearfix">
				    		<label>Senha:</label>
				    		<div class="input"><input type="password" maxlength="16" class="span2-5" name="senha" id="uruario.password"></div>
				    	</div>
				    	<a href="http://localhost:8080/fuxico/conta">Nova Conta</a>

				    	<button type="button" id="logar" class="btn small">Entrar</button>
				    </form>
                </div>
				<div id="logo"></div>
			</div>
		</div>
	</body>
</html>