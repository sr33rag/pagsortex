package net.actions;

import java.util.Map;
import java.util.HashMap;
import net.actions.IAction;

public class ActionMap {

  private static Map<String,IAction> actionMap=null;
  private static ActionMap actionMapInstance=null;

  private ActionMap(Map<String,IAction> _actionMap) {
    actionMap=_actionMap;
  }

  public static ActionMap createActionMap() {
    if(actionMap == null && actionMapInstance == null)
      actionMapInstance = new ActionMap(new HashMap<String,IAction>());
    return actionMapInstance;
  }

  public static ActionMap createActionMap(Map<String,IAction> _actionMap) {
    if(actionMap == null && actionMapInstance == null)
     actionMapInstance=new ActionMap(_actionMap);
    return actionMapInstance;
  }

  public IAction getAction(String cmd) throws Exception {
    if(actionMap.containsKey(cmd))
      return actionMap.get(cmd);
    else
      throw new Exception("No such action found!");
  }

  public void setAction(String cmd, IAction action) throws Exception {
    if(actionMap.containsKey(cmd))
      throw new Exception("Command "+cmd+" already bound with another action");
    else
      actionMap.put(cmd,action);
  }

}