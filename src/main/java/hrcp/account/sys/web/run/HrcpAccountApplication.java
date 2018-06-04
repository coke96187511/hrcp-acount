package hrcp.account.sys.web.run;


import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

import hrcp.comm.constants.CommConst;

@SpringBootApplication
@EnableJpaRepositories("hrcp.account.sys.dao")
@ComponentScan("hrcp.account.sys")
@PropertySource("jdbc.properties")
@EnableTransactionManagement
@EnableDiscoveryClient
public class HrcpAccountApplication {

	@Autowired  
    private Environment env;  
	
	public static void main(String[] args) {
		SpringApplication.run(HrcpAccountApplication.class, args);
	}
	
	
      
    @Bean  
    public HibernateExceptionTranslator hibernateExceptionTranslator() {  
        return new HibernateExceptionTranslator();  
    }  

    @Bean  
    public DataSource dataSource() {  
        DruidDataSource source = new DruidDataSource();  
        try {
			source.setFilters("config");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        source.setDriverClassName(env.getRequiredProperty(CommConst.PROP_JDBC_DRIVER));  
        source.setUrl(env.getRequiredProperty(CommConst.PROP_JDBC_URL));  
        source.setUsername(env.getRequiredProperty(CommConst.PROP_JDBC_USERNAME));  
        source.setPassword(env.getRequiredProperty(CommConst.PROP_JDBC_PASSWORD));
        source.setConnectionProperties("config.decrypt=true");
        source.setMaxActive(20);
        source.setInitialSize(2);
        source.setMinIdle(1);
        source.setTimeBetweenEvictionRunsMillis(60000);
        source.setMaxWait(60000);
        source.setMinEvictableIdleTimeMillis(300000);
        source.setValidationQuery("SELECT 'x'");
        source.setTestWhileIdle(true);
        source.setTestOnReturn(false);
        source.setTestOnReturn(true);
        source.setMaxPoolPreparedStatementPerConnectionSize(20);  
        source.setDefaultAutoCommit(true);
        return source;  
    }  
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();  
		entityManagerFactory.setDataSource(dataSource);
//		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class); 
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactory.setPackagesToScan("hrcp.account.sys.bean");  
//		entityManagerFactory.setJpaProperties(hibernateProperties());   
		entityManagerFactory.afterPropertiesSet();  
        EntityManagerFactory factory = entityManagerFactory.getObject();
		return factory;
	}
	
	@Bean  
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager manager = new JpaTransactionManager();  
        manager.setEntityManagerFactory(entityManagerFactory);  
        return manager;  
    }  
}
