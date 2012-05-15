<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon"
			href="${contextPath}/imagens/icones/fuxico.ico" type="image/x-icon" />
		<title>Controle de Acesso</title>
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js">
</script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js">
</script>
		<script src="${contextPath}/js/md5.js">
</script>

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
				required : "Digite o Usuário."
			},
			"senha" : {
				required : "Digite a Senha."
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
				required : "Digite o Usuário."
			},
			"usuario.nome" : {
				required : "Digite Nome Completo."
			},
			"usuario.senha" : {
				required : "Digite a Senha."
			},
			"usuario.email" : {
				required : "Digite o Email."
			}
		}
	});
});
</script>
		<style type="text/css" media="screen">
#logo {
	vertical-align: top;
	background-color: transparent;
	z-index: 6;
}

#logo {
	vertical-align: top;
	background-color: transparent;
	z-index: 6;
}

#principal {
	width: 900px;
	height: 460px;
	margin: 8px auto;
	border: 1px solid #000000;
	z-index: 1;
	opacity: .8;
	background-color: white;
}

#login {
	width: 55.6%;
	height: 585px;
	margin: 5px auto 2px 3px;
	border: 0px solid;
	z-index: 3;
	float: left;
}

td {
	font-family: verdana, arial;
	font-size: 8pt;
}

.estilotabela {
	background-color: ffffff;
	border-style: solid;
	border-color: 666666;
	border-width: 1px;
}

.estilotitulo {
	background-color: ddeeff;
	color: 333333;
	font-weight: bold;
	font-size: 10pt;
}

#erro {
	color: Red;
}
</style>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type"
			content="text/html; charset=ISO-8859-1">
	</head>
	<body background="imagens/wallpaper.jpg">
		<div id="logo">
			&nbsp;
			<img alt="Fuxico" src="imagens/logo.png" />
		</div>

		<div id="principal">
			<table border="0" style="height: 100%; width: 100%;">
				<tr>
					<td style="width: 50%;">
						<table border="0" style="height: 100%; width: 100%;">
							<tr>
								<td>
									<table class="estilotabela" width="100%" cellpadding="2"
										cellspacing="2" border="0">
										<tr>
											<td class="estilotitulo">
												<p align="center">
													Acesso ao Fuxico
												</p>
											</td>
										</tr>
										<tr>
											<td>
												<form method="post" id="formLogin" action="">
													<table border="0" width="100%">
														<tr>
															<td colspan="2">
																<div id="erro">
																	<p align="center" style="color: Red;">
																		${erro}
																	</p>
																</div>
															</td>
														</tr>
														<tr>
															<td width="80px">
																<p align="right">Usuário:&nbsp;</p>
															</td>
															<td>
																<input type="text" maxlength="32" name="login" id="usuarioLogin">
															</td>
														</tr>
														<tr>
															<td>
																<p align="right">Senha:&nbsp;</p>
															</td>
															<td>
																<input type="password" maxlength="16" class="span2-5"
																	name="senha" id="senha">
															</td>
														</tr>
														<tr>
															<td colspan="2" align="right" valign="center">
																<table border="0">
																	<tr width="100%" valign="top">
																		<td>
																			<button type="submit" id="logar" class="btn primary">&nbsp;Entrar&nbsp;</button>
																			</form>
																		</td>
																		<td>
																			<form
																				action="/fuxico/servlet/LoginServlet?identifier=https://www.google.com/accounts/o8/id"
																				method="post">
																				<input style="width: 60px; height: 25px;" src="imagens/loginwithgoogle.PNG"
																					type="image"
																					value=" " />
																			</form>
																		</td>
																	</tr>
																</table>

															</td>
														</tr>
													</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="estilotabela" width="100%" cellpadding="2"
										cellspacing="2" border="0">
										<tr>
											<td class="estilotitulo">
												<p align="center">
													Novos Fuxiqueiros
												</p>
											</td>
										</tr>
										<tr>
											<td>
												<form method="post" id="formConta" action="/fuxico/conta">
													<table border="0" height="100%" width="100%">
														<tr>
															<td colspan="2" height="40px">
																<div id="erroConta">
																	<p align="center" style="color: Red;">
																		${erroConta}
																	</p>
																</div>
																<div id="ok">
																	<p align="center" style="color: Blue;">
																		${ok}
																	</p>
																</div>
															</td>
														</tr>
														<tr>
															<td width="110px">
																<p align="right">
																	Nome Completo:&nbsp;
																</p>
															</td>
															<td>
																<input class="nome" type="text" maxlength="255"
																	name="usuario.nome">
															</td>
														</tr>
														<tr>
															<td>
																<p align="right">
																	Usuário:&nbsp;
																</p>
															</td>
															<td>
																<input type="text" class="campo" maxlength="32"
																	name="usuario.login">
															</td>
														</tr>
														<tr>
															<td>
																<p align="right">
																	Senha:&nbsp;
																</p>
															</td>
															<td>
																<input type="password" class="campo" maxlength="32"
																	name="usuario.senha">
															</td>
														</tr>
														<tr>
															<td>
																<p align="right">
																	Confirma:&nbsp;
																</p>
															</td>
															<td>
																<input type="password" width="120" />
															</td>
														</tr>
														<tr>
															<td>
																<p align="right">
																	Email:&nbsp;
																</p>
															</td>
															<td>
																<input class="email" type="text" maxlength="64"
																	name="usuario.email">
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<p align="right">
																	<button type="submit" id="criar" class="btn primary">
																		Criar Conta
																	</button>
																</p>
															</td>
														</tr>
													</table>
												</form>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td style="width: 50%;">
						<img src="imagens/festa1.jpg">
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>