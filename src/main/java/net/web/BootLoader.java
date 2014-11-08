package net.web;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.sql.DataSource;

import net.dao.DaoFactory;
import net.util.PagingHelper;
import net.util.Configuration;

public class BootLoader implements ServletContextListener {

  private Logger logger=Logger.getLogger(BootLoader.class.getName());
  private Context ctx=null;
  private DataSource ds=null;
  private DaoFactory daoFactory=null;
  private PagingHelper pagingHelper=null;
  private Configuration conf=null;

  public void contextInitialized(ServletContextEvent event) {
    try {
      ctx = new InitialContext();
      ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pagsortexds");
    } catch (Exception ex) {
      logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
    daoFactory = DaoFactory.getFactory(ds);
    pagingHelper = PagingHelper.getInstance();
    conf = Configuration.getConfigInstance(daoFactory, pagingHelper);
    event.getServletContext().setAttribute("appConfig",conf);
  }

  public void contextDestroyed(ServletContextEvent event) {}

}
