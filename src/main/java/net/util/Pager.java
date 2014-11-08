package net.util;

import java.io.Serializable;
import java.util.Collection;

public interface Pager<T extends Serializable> extends Serializable {

  public int getNumOfPages();

  public Collection<T> getUpToNow();
  
  public Collection<T> getNext(int currPageNum);
  
  public Collection<T> getPrevious(int currPageNum);
  
  public Collection<T> getPage(int index);
  
  public void addToCollection(Collection<T> newRex);
  
}
