package net.actions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.actions.IAction;
import net.util.Configuration;

public abstract class AbstractAction implements IAction {

  protected String url=null,queryString=null;
  protected ServletContext application=null;
  protected HttpSession sess=null;
  protected HttpServletRequest req=null;
  protected HttpServletResponse res=null;
  protected Configuration appConfig=null;

  public void execute(HttpServletRequest _req, HttpServletResponse _res) throws Exception {
	this.req = _req;
	this.res = _res;
	this.application = req.getServletContext();
	appConfig=(net.util.Configuration)application.getAttribute("appConfig");
	sess=req.getSession(false);
	queryString=req.getQueryString();
    preProcess();
	processRequest();
	postProcess();
  } 

  public abstract void preProcess() throws Exception;
  public abstract void processRequest() throws Exception;
  public abstract void postProcess() throws Exception;

  public final void redirect() throws Exception {
	res.sendRedirect(req.getContextPath()+"/"+url+appConfig.getEnding());
  }

  public final void forward() throws Exception {
	application.getRequestDispatcher("/"+url+".jsp").forward(req, res);
  }
}
