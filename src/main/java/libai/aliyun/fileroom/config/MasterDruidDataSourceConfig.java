package libai.aliyun.fileroom.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
public class MasterDruidDataSourceConfig {

    @Value("${master.druid.datasource.url}")
    private String dbUrl;

    @Value("${master.druid.datasource.username}")
    private String username;

    @Value("${master.druid.datasource.password}")
    private String password;

    @Value("${master.druid.datasource.driverClassName}")
    private String driverClassName;

    @Value("${master.druid.datasource.initialSize}")
    private int initialSize;

    @Value("${master.druid.datasource.minIdle}")
    private int minIdle;

    @Value("${master.druid.datasource.maxActive}")
    private int maxActive;

    @Value("${master.druid.datasource.maxWait}")
    private int maxWait;

    @Value("${master.druid.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${master.druid.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${master.druid.datasource.validationQuery}")
    private String validationQuery;

    @Value("${master.druid.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${master.druid.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${master.druid.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${master.druid.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${master.druid.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${master.druid.datasource.filters}")
    private String filters;

    @Value("{master.druid.datasource.connectionProperties}")
    private String connectionProperties;

    @Autowired
    WallFilter wallFilter;

    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        /** configuration */
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource
                .setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource
                .setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
        }
        datasource.setConnectionProperties(connectionProperties);
        List<Filter> filters = new ArrayList<>();
        filters.add(wallFilter);
        datasource.setProxyFilters(filters);
        return datasource;
    }


    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    public WallFilter wallFilter(WallConfig wallConfig){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

    @Bean(name = "wallConfig")
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        // 允许一次执行多条语句
        wallConfig.setMultiStatementAllow(true);
        wallConfig.setNoneBaseStatementAllow(true);
        return wallConfig;
    }


}