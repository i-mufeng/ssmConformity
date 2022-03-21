# 整合SSM框架

> author：小小小沐风
> mail：mufeng.yu@imufeng.cn

## 7.1 Spring+SpringMVC

1. 新建web工程
2. 引入依赖
3. 配置MVC相关
    1. 配置DispatcherServlet
    2. 配置SpringMVC
    3. 解决乱码问题
        1. 请求乱码
        2. 响应乱码
    4. 配置Freemarker模板引擎

### 1、引入依赖：

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
<!--SpringMVC-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
<!--Spring上下文-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>${spring.version}</version>
</dependency>
<!--Freemarker模板引擎-->
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.31</version>
</dependency>
<!--Jackson-->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.2</version>
</dependency>
<!--文件上传-->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

### 2、配置DispatcherServlet

```xml
<servlet>
    <servlet-name>ssmConformity</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </init-param>
    <!--优先级-->
    <load-on-startup>0</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>ssmConformity</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

### 3、配置SpringMVC相关

```xml
    <!--包扫描-->
    <context:component-scan base-package="com.mufeng"/>
    <!--注解开发模式驱动-->
    <mvc:annotation-driven/>
    <!--放行静态文件-->
    <mvc:default-servlet-handler/>
```

### 4、乱码解决

- 请求乱码

  ```xml
  <!--字符过滤器，解决POST请求中的乱码问题-->
  <filter>
      <filter-name>character-filter</filter-name>
      <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
      <init-param>
          <param-name>encoding</param-name>
          <param-value>UTF-8</param-value>
      </init-param>
  </filter>
  <filter-mapping>
      <filter-name>character-filter</filter-name>
      <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

- 响应乱码

  ```xml
  <!--注解开发模式驱动-->
  <mvc:annotation-driven>
      <!--解决乱码问题-->
      <mvc:message-converters>
          <bean class="org.springframework.http.converter.StringHttpMessageConverter">
              <property name="defaultCharset" value="UTF-8"/>
  <!--            <property name="supportedMediaTypes">-->
  <!--                <list>-->
  <!--                    <value>text/html;charset=UTF-8</value>-->
  <!--                    <value>application/json;charset=UTF-8</value>-->
  <!--                </list>-->
  <!--            </property>-->
          </bean>
      </mvc:message-converters>
  </mvc:annotation-driven>
  ```

### 5、配置Freemarker模板引擎

```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
    <!--视图解析器将模板引擎与数据结合后返回的字符集-->
    <property name="contentType" value="text/html;charset=UTF-8"/>
    <!--后缀-->
    <property name="suffix" value=".ftl"/>
</bean>

<!--freemarker配置-->
<bean id="freeMarkerConfigurer" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
    <!--前缀，模板引擎的位置。TOMCAT不支持对 freemarker的天然解析-->
    <property name="templateLoaderPath" value="/WEB-INF/ftl/"/>
    
    <!--freeMarker的本地配置文件-->
    <!--<property name="configLocation" value=""/>-->
    <property name="freemarkerSettings">
        <props>
            <!--对模板引擎的配置-->
            <prop key="defaultEncoding">UTF-8</prop>
        </props>
    </property>
</bean>
```

### 6、配置文件上传解析器

```xml
<!--文件上传配置-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="defaultEncoding" value="UTF-8"/>
    <property name="maxUploadSize" value="20480000"/>
</bean>
```

## 7.2 Spring+SpringMVC+MyBatis

1. 引入依赖
2. 配置数据源

### 1、引入依赖

```xml
<!--   *********   整合Mybatis   **********   -->

<!--mybatis-->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.9</version>
</dependency>
<!--数据库连接工具-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>
<!--分页插件-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.3.0</version>
</dependency>
<!--Mybatis整合Spring框架-->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.7</version>
</dependency>
<!--spring-jdbc-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>
<!--Druid监控和连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
```

### 2、配置数据源和连接池

```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimeZone=Asia/Shanghai
jdbc.username=root
jdbc.password=root
```

```xml
<!--引入配置文件，对标 MyBatis 配置文件中的 <properties resource="jdbc.properties"/>-->
<context:property-placeholder location="classpath:jdbc.properties"/>
<!--DAO配置-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
    <!--最大活跃连接数量，默认为8-->
    <property name="maxActive" value="10"/>
    <!--初始化连接数量-->
    <!--<property name="initialSize" value="6"/>-->
</bean>

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:mappers/*.xml"/>
</bean>

<!--扫描MyBatisDAO中的接口-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.mufeng.dao"/>
</bean>
```

### 3、Spring事务管理

```xml
<!--事务管理-->
<!--基于数据源的事务管理器-->
<bean id="dataSourceTransactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
<!--开启声明式事务-->
<tx:annotation-driven transaction-manager="dataSourceTransactionManager"/>
```

### 4、配置跨域访问

```xml
<!--配置跨域访问-->
<mvc:cors>
    <mvc:mapping path="/**" 
                 allowed-origins="*" 
                 allowed-methods="*" 
                 allowed-headers="*" 
                 allow-credentials="true"/>
</mvc:cors>
```

## 7.3 整合其他常用组件

### 1、整合logback日志

```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

logback.xml：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!--根标签。属性：
        scan="true"             如果该配置文件发生变化，则会重新加载
        scanPeriod="1 minute"   检查配置文件变化的时间间隔
        debug="true"            默认为 false 不会实时检测 logback的运行状况

-->
<configuration debug="false"
        xmlns="http://ch.qos.logback/xml/ns/logback" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://ch.qos.logback/xml/ns/logback
        https://raw.githubusercontent.com/enricopulatzo/logback-XSD/master/src/main/xsd/logback.xsd">
    <!--
    代表写日志的组件，可以有很多个
        必须属性：
            name 唯一标识
            class 指定class的全限定名
                ConsoleAppender     将日志输出到控制台
                FileAppender        将日志添加到文件中
                RollingFileAppender 可以配合一些滚动策略将日志写入文件中
    -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--格式化-->
        <encoder>
            <charset>UTF-8</charset>
            <!--格式      室间隔是        线程  日志级别（宽度5左对齐） 全类名（最多30字符） -->
            <pattern>%d{HH:mm:ss.SSS}**%thread**%-5level %logger{30} %msg%n</pattern>
        </encoder>
    </appender>
    
    <!--
    level：日志级别
        error warn info debug trance
    -->
    <root level="trace">
        <appender-ref ref="console"/>
    </root>
</configuration>
```

### 2、整合Spring-test

```xml
<!--测试相关-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>${spring.version}</version>
</dependency>
```



```java
/*junit提供，可以运行指定环境
SpringRunner.class是SpringJUnit4ClassRunner别名
配合Junit为其提供Spring上下文环境*/
@RunWith(SpringRunner.class)
/*Spring-test指定Spring配置文件或者配置类位置的注解*/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
/*确定是Web环境，用来指定webapp目录位置*/
@WebAppConfiguration
public class AppTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testStartUp() {
        Student student = studentDao.queryById(1);
        System.out.println(student);

    }
}
```

### 3、整合Swagger2

```xml
<!--Swagger相关-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```



```java
/**
 * @author MFGN
 * @data 2022/3/21 17:38
 * @description Swagger2配置文件
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                /*包含哪些api*/
                .apis(RequestHandlerSelectors.any())
                /*扫描注解*/
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ssmConformity整合项目")
                .description("ssm框架整合阶段性项目")
                .version("v1.0")
                .termsOfServiceUrl("http://www.imufeng.cn")
                .build();
    }
}
```

其他的配置等见源码
