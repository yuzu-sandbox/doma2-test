package test;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import javax.sql.DataSource;

@SingletonConfig
public class AppConfig implements Config {

  private static final AppConfig INSTANCE = new AppConfig();
  private final Dialect dialect;
  private final LocalTransactionDataSource localTransactionDataSource;
  private final TransactionManager transactionManager;

  private AppConfig() {
    dialect = new SqliteDialect();

    SimpleDataSource dataSource = new SimpleDataSource();
    dataSource.setUrl("jdbc:sqlite:test.db");
    localTransactionDataSource = new LocalTransactionDataSource(dataSource);
    transactionManager = new LocalTransactionManager(localTransactionDataSource.getLocalTransaction(getJdbcLogger()));
  }

  public static AppConfig singleton() {
    return INSTANCE;
  }

  @Override
  public DataSource getDataSource() {
    return localTransactionDataSource;
  }

  @Override
  public Dialect getDialect() {
    return dialect;
  }

  @Override
  public TransactionManager getTransactionManager() {
    return transactionManager;
  }
}
