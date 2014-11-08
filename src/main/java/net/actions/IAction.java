package net.actions;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction extends Serializable {
  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}