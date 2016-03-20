import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;

import javax.sql.DataSource;

@SingletonConfig
public class AppConfig implements Config {

  private static final AppConfig INSTANCE = new AppConfig();
  private final Dialect dialect;
  private final LocalTransactionDataSource localTransactionDataSource;

  private AppConfig() {
    dialect = new SqliteDialect();

    SimpleDataSource dataSource = new SimpleDataSource();
    dataSource.setUrl("jdbc:sqlite:test.db");
    localTransactionDataSource = new LocalTransactionDataSource(dataSource);
  }

  public static AppConfig getInstance() {
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
}
