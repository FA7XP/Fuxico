package br.com.fa7.fuxico.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.*;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;

public class LoginServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;
	private ConsumerManager consumerManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		config.getServletContext();
		this.consumerManager = new ConsumerManager();
	}

	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpResp) throws ServletException, IOException {
		ParameterList response = new ParameterList(httpReq.getParameterMap());

		DiscoveryInformation discovered = (DiscoveryInformation) httpReq.getSession().getAttribute("openid-disc");

		StringBuffer receivingURL = httpReq.getRequestURL();
		String queryString = httpReq.getQueryString();
		if (queryString != null && queryString.length() > 0)
			receivingURL.append("?").append(httpReq.getQueryString());

		try {
			VerificationResult verification = consumerManager.verify(receivingURL.toString(), response, discovered);
			
			if (verification.getVerifiedId() != null) {
				AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

				if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
					FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);

					String email = (String) fetchResp.getAttributeValues("email").get(0);
					String firstName = (String) fetchResp.getAttributeValues("firstName").get(0);
					String lastName = (String) fetchResp.getAttributeValues("lastName").get(0);
					String nome = firstName + " " + lastName;  
					String senha = verification.getVerifiedId().toString();
					httpResp.sendRedirect("../google/" + firstName + "/" + senha.replace("https://www.google.com/accounts/o8/id?id=", "") + "/" + nome + "/" + email);
				}else
					System.out.println("Login Falhou.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpResp)	throws ServletException, IOException {
		String identifier = httpReq.getParameter("identifier");
		try {
			String returnToUrl = httpReq.getRequestURL().toString();
			DiscoveryInformation discovered = consumerManager.associate(consumerManager.discover(identifier));

			httpReq.getSession().setAttribute("openid-disc", discovered);

			AuthRequest authReq = consumerManager.authenticate(discovered, returnToUrl);

			FetchRequest fetch = FetchRequest.createFetchRequest();
			if (identifier.startsWith("http://www.google.com/accounts/o8/id")) {
				fetch.addAttribute("email",	"http://axschema.org/contact/email", true);
				fetch.addAttribute("firstName",	"http://axschema.org/namePerson/first", true);
				fetch.addAttribute("lastName", "http://axschema.org/namePerson/last", true);
			} else { 
				fetch.addAttribute("fullname", "Invalido", true);
				fetch.addAttribute("email",	"Invalido", true);
			}
			authReq.addExtension(fetch);

			httpResp.sendRedirect(authReq.getDestinationUrl(true));

		} catch (OpenIDException e) {
			e.printStackTrace();
		}
	}
}