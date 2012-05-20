<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery-ui-1.8.13.custom.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.ui.datepicker-pt-BR.js"></script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js"></script>

		<link rel="stylesheet" type="text/css" href="css/fuxico.css" />

		<title>Fuxico</title>
		<style type="text/css" media="screen">
			#logo {
				vertical-align: top;
				background-color: transparent;
				z-index: 6;
				position: absolute;
			}
			
			#principal {
				width: 800px;
				height: 610px;
				margin: 0px auto;
				border: 0px solid #000000;
				z-index: 1;
				opacity: .8;
				background-color: transparent;
			}
			
			#fuxicos {
				width: 100%;
				height: 460px;
				margin: 5px auto 2px 3px;
				z-index: 3;
				float: left;
				overflow-y: scroll;"
			}
			
			#digitar_fuxico {
				width: 97.5%;
				height: 110px;
				margin: 5px auto 2px 3px;
				border: 0px solid;
				z-index: 5;
				background-color: ffffff;
				border-style: solid;
				border-color: 666666;
				border-width: 1px;
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
				font-size: 8pt;
				height: 15px;
			}
		</style>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type"	content="text/html; charset=ISO-8859-1">

	</head>

	<body background="imagens/wallpaper.jpg">

		<div id="logo">
			<img alt="Fuxic" src="imagens/logo.png" />
		</div>

		<div id="principal">
		
			<table border="0" style="width: 100%; height: 100%;">
				<tr>
					<td align="right">
						Voltar
					</td>
				</tr>
				
				<tr>
					<td style="width: 200px;">
						<table border="0" style="width: 100%; height: 100%;">
							<tr>
								<td style="height: 200px;">
									<table class="estilotabela" width="100%" height="200px;" cellpadding="2" cellspacing="2">
										<tr>
											<td class="estilotitulo">
												<p align="center">
													Informações
												</p>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" height="170px" width="100%">
													<tr>
														<td>
															<p align="center">
																<a href='link/${usuario.id}'> <img src="imagens/anonimo.jpg" /> </a>
															</p>
														</td>
														<td>
															<table>
																<tr>
																	<td>
																		${usuario.nome}
																	</td>
																</tr>
																<tr>
																	<td>
																		<a href='link/${usuario.id}'>@${usuario.login}</a>
																	</td>
																</tr>
																<tr>
																	<td>
																		${usuario.email}
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
									<table class="estilotabela" width="100%" height="100%"
										cellpadding="2" cellspacing="2" border="0">
										<tr>
											<td class="estilotitulo">
												<p align="center">
													Fuxiqueiros
												</p>
											</td>
										</tr>
										<tr>
											<td valign="top">
												<div style="width: 100%; height: 100%; z-index: 1; overflow-y: scroll;">
													<table border="0" height="170px" width="100%">
														<tr>
															<td>
																<c:forEach items="${fuxiqueiros}" var="fuxiqueiro">
																	<table border="0" height="20px" width="100%">
																		<tr>
																			<td width="50px">
																				<p align="center">
																					<img style="width: 40px;" src="imagens/anonimo.jpg" />
																				</p>
																			</td>
																			<td>
																				<table>
																					<tr>
																						<td>
																							${fuxiqueiro.nome}
																						</td>
																					</tr>
																					<tr>
																						<td>
																							<a href='link/${fuxiqueiro.id}'>@${fuxiqueiro.login}</a>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</c:forEach>
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" style="width: 100%; height: 100%;">
							<c:if test="${exibirFuxicar}">
								<tr>
									<td style="height: 100px;">
										<form id="formLogin" method="post"	action="<c:url value='/fuxicar/${usuario.id}'/>">
											<table border="0" style="width: 50px;">
												<tr>
													<td>
														<textarea name="mensagem" cols="44" rows=3></textarea>
													</td>
												</tr>
												<tr>
													<td>
														<p align="right">
															<input type="submit" value="Fuxicar">
														</p>
													</td>
												</tr>
											</table>
										</form>
									</td>
								</tr>
							</c:if>
							<tr>
								<td>
									<table class="estilotabela" width="75%" height="100%" cellpadding="2">
										<tr>
											<td class="estilotitulo">
												<p align="center">
													Fuxicos
												</p>
											</td>
										</tr>
										<tr>
											<td>
												<div id="fuxicos">
												</div>   
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript">
			$(function() {
				window.setInterval("listar()", 10000);
				listar();
			});
			function listar() {
				var url = "/fuxico/fuxicos/${usuario.id}";
				$
						.ajax( {
							url : url,
							type : "GET",
							cache : false,
							success : function(retorno) {
								var fuxicos = retorno.fuxicos;
								$("#fuxicos").html("");
								$(fuxicos)
										.each(
												function(i, fuxico) {
													$("#fuxicos")
															.prepend(
																	"<table border='0' height='20px' width='100%'>" +
																			"<tr fuxicoid='"+ fuxico.id + "'>"+
																			"<td width='50px'>"+
																				"<p align='center'>"+
																					"<img style='width: 40px;' src='imagens/anonimo.jpg' />"+
																				"</p>"+
																			"</td>"+
																			"<td>"+
																				"<table border='0' valign='top'>"+
																					"<tr>"+
																						"<td colspan='2'>"+
																							fuxico.data +
																						"</td>"+
																					"</tr>"+
																					"<tr>"+
			
																						"<td>"+
																							"<span class='nomeInfo'>"+
																								fuxico.fuxico+ "</span>"+
																						"</td>"+
																					"</tr>"+
																				"</table>"+
																			"</td>"+
																		"</tr>" +
																	"</table>");
																		
												});
							},
							error : function() {
							}
						});
			}
		</script>
	</body>
</html>