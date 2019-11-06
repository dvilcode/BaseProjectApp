package $packageName.managedbeans;

import java.io.Serializable;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import $packageName.utils.Parameters;

/**
 * Clase BrowserHistoryBean
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name = "BrowserHistoryController")
@SessionScoped
public class BrowserHistoryBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LinkedList<String> pageStack = new LinkedList<>();
	private Integer maxHistoryStackSize = 20;

	public void addPageUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		String fullUrl = null;
		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			if (servletRequest.getAttribute(Parameters.FORWARD_URI) == null) {
				fullUrl = servletRequest.getRequestURI();
				if (servletRequest.getQueryString() != null && !servletRequest.getQueryString().equals("")) {
					fullUrl += "?" + servletRequest.getQueryString();
				}
				updatePageStack(fullUrl);
			} else {
				fullUrl = (String) servletRequest.getAttribute(Parameters.FORWARD_URI);
				if (servletRequest.getAttribute(Parameters.FORWARD_QUERY_STRING) != null && !servletRequest.getAttribute(Parameters.FORWARD_QUERY_STRING).toString().equals("")) {
					fullUrl += "?" + servletRequest.getAttribute(Parameters.FORWARD_QUERY_STRING).toString();
				}
				updatePageStack(fullUrl);
			}
		}
	}

	public String getBackUrl() {
		Integer stackSize = pageStack.size();
		if (stackSize > 1) {
			return pageStack.get(1);
		}
		// Just in case hasPageBack was not implemented (be safe)
		return pageStack.get(0);
	}

	public boolean hasPageBack() {
		return pageStack.size() > 1;
	}

	private void updatePageStack(String navigationCase) {

		Integer stackSize = pageStack.size();

		// If stack is full, then make room by removing the oldest item
		if (stackSize >= maxHistoryStackSize) {
			pageStack.remove(0);
			stackSize = pageStack.size();
		}

		// If the first page visiting, add to stack
		if (stackSize == 0) {
			pageStack.push(navigationCase);
			return;
		}

		// If it appears the back button has been pressed, in other words:
		// If the A -> B -> C, and user navigates from C -> B, then remove C
		if (stackSize > 1 && pageStack.get(1).equals(navigationCase)) {
			pageStack.remove(0);
			return;
		}

		// If we are on the same page
		// If A == A then ignore
		if (pageStack.get(stackSize - 1).equals(navigationCase)) {
			return;
		}

		// In a normal case, we add the item to the stack
		if (stackSize >= 1) {
			pageStack.push(navigationCase);
			return;
		}

	}

	public LinkedList<String> getPageStack() {
		return pageStack;
	}

	public void setPageStack(LinkedList<String> pageStack) {
		this.pageStack = pageStack;
	}
}