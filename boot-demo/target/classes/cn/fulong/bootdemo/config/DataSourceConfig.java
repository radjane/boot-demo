//package cn.fulong.bootdemo.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//
///**
// * @Author:GHB
// * @Date:2019-07-03
// */
//@PropertySource("classpath:database.properties")
//@Configuration
//public class DataSourceConfig {
//
//    /***
//     * 数据源 1
//     * @return
//     */
//    @Qualifier(value = "firstDataSource")
//    @Bean(value = "firstDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.first")
//    public DataSource firstDataSource() {
//
//
//        return DataSourceBuilder.create().build();
//    }
//
//
//    /***
//     * 数据源 2
//     * @return
//     */
//    @Bean(value = "secondDataSource")
//    @Qualifier(value = "secondDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.second")
//    public DataSource secondDataSource() {
//
//
//        return DataSourceBuilder.create().build();
//    }
//
//
//    /***
//     * 数据源1 的jdbc
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "firstJdbcTemplate")
//    public JdbcTemplate firstJdbcTemplate(@Qualifier(value = "firstDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    /***
//     * 数据源2 的jdbc
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "secondJdbcTemplate")
//    public JdbcTemplate secondJdbcTemplate(@Qualifier(value = "secondDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//
//}
