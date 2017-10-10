package edu.msg.ro.access;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filter for redirrecting unauthenticated users.
 * 
 * @author balinc
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "*.xhtml" })
public class AuthenticationFilter implements Filter {

	/**
	 * Init method(not userd).
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing because of implemented Filter but not used.
	}

	/**
	 * Filter method for redirrecting to login page.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(false);

		String requestUrl = httpRequest.getRequestURI();

		boolean isLoginPage = requestUrl.indexOf("/login.xhtml") >= 0;
		boolean isUserLoggedIn = httpSession != null && httpSession.getAttribute("username") != null;
		boolean isResource = requestUrl.contains("javax.faces.resource");

		if (isLoginPage || isUserLoggedIn || isResource) {
			if (isLoginPage && isUserLoggedIn) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/bugManagment.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
		}

	}

	/**
	 * Destroy method(not used).
	 */
	@Override
	public void destroy() {
		// Do nothing because of implemented Filter but not used.
	}
}