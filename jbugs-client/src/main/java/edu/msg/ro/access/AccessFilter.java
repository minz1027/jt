package edu.msg.ro.access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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

import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.security.PermissionChecker;

/**
 * Filter for redirrecting unautorized users.
 * 
 * @author laszll
 *
 */
@WebFilter(filterName = "AccessFilter", urlPatterns = { "*.xhtml" })
public class AccessFilter implements Filter {

	@EJB
	private PermissionChecker permissionChecker;

	@EJB
	private UserFacade userFacade;

	/**
	 * Init method(not userd).
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing because of implemented Filter but not used.
	}

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(true);

		if (httpSession.getAttribute("username") == null) {
			chain.doFilter(request, response);
		} else {

			if (canAcessPage(httpRequest.getRequestURI(), (String) httpSession.getAttribute("username"))) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/bugManagment.xhtml");
			}
		}

	}

	private boolean canAcessPage(String requestUrl, String username) {
		List<Long> permissionList = new ArrayList<>();

		if (requestUrl.indexOf("/permissionManager.xhtml") >= 0) {
			permissionList.add(1L);
		} else if (requestUrl.indexOf("/userManagment.xhtml") >= 0) {
			permissionList.add(2L);
		} else if (isIncluded(requestUrl)) {
			return false;
		} else {
			return true;
		}

		return permissionChecker.canAccess(userFacade.getUserByUsername(username), permissionList);
	}

	private boolean isIncluded(String requestUrl) {
		return requestUrl.indexOf("/user/userEdit.xhtml") >= 0 || requestUrl.indexOf("/user/userCreate.xhtml") >= 0
				|| requestUrl.indexOf("/bug/bugCreate.xhtml") >= 0 || requestUrl.indexOf("/bug/bugEdit.xhtml") >= 0
				|| requestUrl.indexOf("/header/header-lang.xhtml") >= 0
				|| requestUrl.indexOf("/header/header-menu.xhtml") >= 0 || requestUrl.indexOf("/header.xhtml") >= 0;
	}

	/**
	 * Destroy method(not used).
	 */
	@Override
	public void destroy() {
		// Do nothing because of implemented Filter but not used.
	}

}
