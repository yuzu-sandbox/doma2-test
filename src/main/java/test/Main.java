package test;

import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("開始");
    SimpleDao dao = new SimpleDaoImpl();
    TransactionManager tm = AppConfig.singleton().getTransactionManager();
    tm.required(() -> {
      List<Sample> samples = dao.findAll();
      for(Sample s : samples) {
        System.out.println(s.toString());
      }

      System.out.println("----------");

      Sample sample = dao.find(1);// yuzuが取得できるはず
      System.out.println(sample.toString());

      System.out.println("終了");
    });
  }
}
