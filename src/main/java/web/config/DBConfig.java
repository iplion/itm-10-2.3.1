package web.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan({"web.config", "web.service", "web.repository", "web.entity"})
@EnableJpaRepositories("web.repository")
@EnableTransactionManagement
public class DBConfig {
    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${hibernate.hbm2ddl.auto}")
    private String mapping;

    @Value("${hibernate.show_sql}")
    private String isShowSql;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("web.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", isShowSql);
        properties.setProperty("hibernate.hbm2ddl.auto", mapping);
        properties.setProperty("hibernate.dialect", dialect);

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
