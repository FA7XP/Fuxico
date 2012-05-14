<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<link rel="shortcut icon"
			href="${contextPath}/imagens/icones/fuxico.ico" type="image/x-icon" />

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>Fuxico</title>

		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js">
</script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js">
</script>
		<script src="${contextPath}/js/md5.js">
</script>

		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/conta.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

		<script>
$(function() {
	$("#usuarioLogin").focus();

	$("#formLogin").validate( {
		rules : {
			"login" : {
				required : true
			},
			"senha" : {
				required : true
			}
		},
		messages : {
			"login" : {
				required : "Digite o nome."
			},
			"senha" : {
				required : "Digite a senha."
			}
		}
	});

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
	<body>
		<div id="geral">
			<div id="login">

				<div id="fuxico"></div>
				<div id="logoLogin">
					<form method="post" id="formLogin" action="">
						<div id="erro">
							${erro}
						</div>
						<div class="clearfix">
							<label>
								Login:
							</label>
							<div class="input">
								<input type="text" maxlength="32" name="login" id="usuarioLogin">
							</div>
						</div>
						<div class="clearfix">
							<label>
								Senha:
							</label>
							<div class="input">
								<input type="password" maxlength="16" class="span2-5"
									name="senha" id="senha">
							</div>
						</div>
						<button type="submit" id="logar" class="btn primary">
							Logar
						</button>
					</form>

					<form action="/fuxico/servlet/LoginServlet?identifier=https://www.google.com/accounts/o8/id" method="post">
						<input class=""	src="imagens/GoogleLogin.jpg" style=" no-repeat scroll 0 0 transparent; height: 15%; float: left; width: 10%;"	type="image" value=" " />
					</form>

					<form method="post" id="formConta" action="/fuxico/conta">
						<div id="contaGeral">
							<div id="conta">
								<div id="erroConta">
									${erroConta}
								</div>
								<div id="ok">
									${ok}
								</div>
								<div class="clearfix">
									<label>
										Login:
									</label>
									<div class="input">
										<input type="text" class="campo" maxlength="32"
											name="usuario.login">
									</div>
								</div>
								<div class="clearfix">
									<label>
										Senha:
									</label>
									<div class="input">
										<input type="password" class="campo" maxlength="32"
											name="usuario.senha">
									</div>
								</div>
								<div class="clearfix">
									<label>
										Nome:
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
								<button type="submit" id="criar" class="btn primary">
									Criar Conta
								</button>
							</div>
						</div>
					</form>
				</div>
				<div id="logo"></div>
			</div>
		</div>
	</body>
</html>