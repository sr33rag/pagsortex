package net.dao;

import java.util.List;

import net.dao.IDao;
import net.vo.StudentBean;

public interface StudentDao extends IDao {

  public boolean saveStudent(StudentBean _student);
  public boolean updateStudent(StudentBean _student);
  public List<StudentBean> getStudentsWithinLimits(int from, int till);
  
}
