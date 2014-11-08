package net.web;

import java.io.IOException;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.actions.IAction;
import net.util.Configuration;

public class ControllerServlet extends HttpServlet {

  private Map<String, IAction> actionMap=null;
  private ServletConfig config=null;
  private ServletContext application=null;
  private Configuration appConfig=null;

  public void init(ServletConfig _config) {
    String ending=null;
    this.config=_config;
    ending=(String)config.getInitParameter("ending");
    this.application=config.getServletContext();
    this.appConfig=(net.util.Configuration)application.getAttribute("appConfig");
    this.appConfig.setEnding(ending);
    application.setAttribute("appConfig",this.appConfig);
    bindUrls();
  }

  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
    String reqPath=req.getRequestURI().substring(req.getContextPath().length());
	//System.out.println("url: "+reqPath);
    IAction action=getAction(reqPath,this.appConfig.getEnding());
    try {
      if(null != action)
	    action.execute(req,res);
     } catch (Exception ex) {
	   System.out.println("Error to servlet: "+ex.getMessage());
       throw new ServletException(ex);
    }
  }

  public void destroy() {}

  private void bindUrls() {
    actionMap=new HashMap<String, IAction>();
    actionMap.put("viewstudents",new net.actions.ViewStudentsAction());
  }

  private IAction getAction(String cmd, String ending) {
    IAction action=null;
    String actionStr=cmd.substring(1,cmd.length()-ending.length());
	//System.out.println("actionStr: "+actionStr);
    if(actionMap.containsKey(actionStr)) 
	 action=actionMap.get(actionStr);    
    return action;
  }

}
