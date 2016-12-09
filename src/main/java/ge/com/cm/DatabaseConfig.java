
package ge.com.cm;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager  = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.entityManagerFactory());
        return transactionManager;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        
        entityManagerFactory.setDataSource(this.dataSource());
        entityManagerFactory.setPackagesToScan("ge.com");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        Map<String,Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put("hibernate.show_sql", true); // logs every sql query if true
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", true);
        jpaProperties.put("org.hibernate.envers.store_data_at_delete", true);
        // jpaProperties.put("hibernate.physical_naming_strategy", "core.util.ImprovedNamingStrategy");
        entityManagerFactory.setJpaPropertyMap(jpaProperties);

        entityManagerFactory.afterPropertiesSet();
        
        return entityManagerFactory.getObject();
    }
    
    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cm");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(10);
        dataSource.setMaxIdle(100);
        dataSource.setMaxActive(100);
        //dataSource.setRemoveAbandoned(true);
        return dataSource;
    }
}
