package net.util;

import javax.sql.DataSource;

import net.dao.DaoFactory;

public class Configuration {

  private static Configuration configInstance=null;
  private static String ending=null;
  private static DaoFactory daoFactory=null;
  private static PagingHelper pagingHelper=null;

  private Configuration(DaoFactory _factory,PagingHelper pH,String _ending) {
	pagingHelper=pH;
    daoFactory=_factory;
	setEnding(_ending);
  }

  private Configuration(DaoFactory _factory,PagingHelper pH) {
	pagingHelper=pH;
    daoFactory=_factory;
  }

  public static Configuration getConfigInstance(DaoFactory _factory, PagingHelper pH, String _ending) {
     if(configInstance==null)
        configInstance = new Configuration(_factory,pH,_ending);
     return configInstance;
  }

  public static Configuration getConfigInstance(DaoFactory _factory, PagingHelper pH) {
     if(configInstance==null)
        configInstance = new Configuration(_factory,pH);
     return configInstance;
  }

  public DaoFactory getDaoFactory() {
     return daoFactory;
  }
  
  public PagingHelper getPagingHelper() {
     return pagingHelper;
  }

  public String getEnding() {
    return ending;
  }

  public void setEnding(String _ending) {
    ending=_ending;
  }
}