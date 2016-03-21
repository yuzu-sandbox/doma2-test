package test;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import test.AppConfig;
import test.Sample;

import java.util.List;

@Dao(config = AppConfig.class)
public interface SimpleDao {
  @Select
  List<Sample> findAll();

  @Select
  Sample find(int id);
}
