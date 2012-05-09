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
		
		<title>Fuxicar</title>
			<style type="text/css" media="screen">
			#topo {
				width: 100%;
				height: 20px;
				z-index: 1;
			}
			
			#principal {
				width: 600px;
				height: 600px;
				margin: 8px auto;
				border: 1px solid #000000;
				z-index: 1;
				opacity: .8;
				background-color: white;
			}
			
			#esquerda {
				width: 40%;
				height: 585px;
				margin: 8px auto;
				border: 0 solid;
				z-index: 2;
				margin: 5px 5px auto;
			}
			
			#fuxicos {
				width: 55.6%;
				height: 585px;
				margin: 5px auto 2px 3px;
				border: 0px solid;
				z-index: 3;
				float: left;
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
			
			#perfil {
				width: 95%;
				height: 200px;
				margin: 8px auto;
				border: 0px solid;
			}
			
			#fuxiqueiros {
				width: 95%;
				height: 200px;
				margin: 8px auto;
				border: 0px solid;
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
		</style>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type"	content="text/html; charset=ISO-8859-1">
		
		
		
	</head>
	<body background= "imagens/wallpaper.jpg" >
		<form id="formLogin" method="post" action="<c:url value='/fuxicar/${usuario.id}'/>">
			<div id="topo"></div>
	
			<div id="principal">
				<div id="esquerda" style="float: left;">
					<div id="perfil" align="center">
						<table class="estilotabela" width="100%" cellpadding="2"
							cellspacing="2">
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
													<img src="imagens/usuario.jpg" />
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
															@obama
														</td>
													</tr>
													<tr>
														<td>
															Estados Unidos
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
					<div id="fuxiqueiros">
						<table class="estilotabela" width="100%" cellpadding="2"
							cellspacing="2">
							<tr>
								<td class="estilotitulo">
									<p align="center">
										Fuxiqueiros
									</p>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" height="170px" width="100%">
										<tr>
											<td>
												<table border="0" height="20px" width="100%">
													<tr>
														<td width="50px">
															<p align="center">
																<img style="width: 40px;"
																	src="imagens/usuario2.jpg" />
															</p>
														</td>
														<td>
															<table>
																<tr>
																	<td>
																		Hillary
																	</td>
																</tr>
																<tr>
																	<td>
																		@hillary
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
	
												<table border="0" height="20px" width="100%">
													<tr>
														<td width="50px">
															<p align="center">
																<img style="width: 40px;"
																	src="imagens/usuario3.jpg" />
															</p>
														</td>
														<td>
															<table>
																<tr>
																	<td>
																		Osama
																	</td>
																</tr>
																<tr>
																	<td>
																		@osama
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
	
												<table border="0" height="20px" width="100%">
													<tr>
														<td width="50px">
															<p align="center">
																<img style="width: 40px;"
																	src="imagens/usuario4.jpg" />
															</p>
														</td>
														<td>
															<table>
																<tr>
																	<td>
																		Valderrama
																	</td>
																</tr>
																<tr>
																	<td>
																		@valderrama
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
						</table>
					</div>
				</div>
				
				<div id="fuxicos">
					<table class="estilotabela" width="100%" cellpadding="2" cellspacing="2">
						<tr>
							<td class="estilotitulo">
								<p align="center">
									Fuxicos
								</p>
							</td>
						</tr>
						<tr>
							<td>
								<div id="mensagens">
									<div class="botao-top"></div>
									<table class="defaultArgsTable table-striped" border="0" height="20px" width="100%">
										<c:forEach items="${fuxicos}" var="fuxico">
											<tr fuxicoid='fuxico.id'><td><span class='nomeInfo'>${fuxico.usuario.nome}: </span>${fuxico.fuxico}</td></tr>	
										</c:forEach>
									</table>
									<div class="botao-bottom"></div>
								</div>
							</td>
						</tr>
					</table>
					<div id="digitar_fuxico">
						<table>
							<tr>
								<td>
									<textarea name="mensagem" COLS=36 ROWS=4></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<p align="right"><input type="submit" value="Fuxicar"/></p>
								</td>
							</tr>
						</table>
					</div>
				</div>
				
			</div>
	    </form>
	    <script type="text/javascript">
			$(function() {
				window.setInterval("listar()", 30000);
			});
			function listar() {
				var url = "/fuxico/fuxicos";
				$.ajax({
					url : url,
					type : "GET",
					cache : false,
					success : function( retorno ) {
						var fuxicos = retorno.fuxicos;
						$(fuxicos).each(function(i, fuxico) {
							$("#mensagens table").prepend("<tr fuxicoid='"+ fuxico.id +"'><td><span class='nomeInfo'>"+ fuxico.usuario.nome +": </span>"+ fuxico.fuxico +"</td></tr>");
						});
					},
					error : function (){}
				});
			}
		</script>
	</body>
</html>