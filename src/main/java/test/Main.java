package test;

import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("いつ恋");
    SimpleDao dao = new SimpleDaoImpl();
    TransactionManager tm = AppConfig.singleton().getTransactionManager();
    tm.required(() -> {
      List<Sample> samples = dao.findAll();
      for(Sample s : samples) {
        System.out.println(s.toString());
      }
      System.out.println("終了");
    });
  }
}
