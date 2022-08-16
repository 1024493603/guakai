package com.practice.springboot.config;

import com.practice.springboot.interceptor.LoginIntercepter;
import com.practice.springboot.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @Component    <bean>
// @Controller  @Service @Repository
// 这四个注解的作用是一样的，下面三个不一样主要是为了区分不同层

// @Configuration用于定义配置类，可以替换xml配置文件，
// 加了这个注解的类的内部包含一个或多个被@Bean注解的方法
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    //等价于下面语句
    /* <bean name="student" class="com.situ.springboot.entity.Student"/>*/
    @Bean
    public Student createStudent() {
        Student student = new Student(1, "zhangsan", 23, "男");
        return student;
    }

    /*<!-- 配置拦截器 -->
   <mvc:interceptors>
      <mvc:interceptor>
         <mvc:mapping path="/**"/>
         <mvc:exclude-mapping path=""/>
         <bean class="com.situ.mvc.interceptor.MyInterceptor1"></bean>
      </mvc:interceptor>
   </mvc:interceptors>*/
    // 这个方法用来注册拦截器，我们写的拦截器需要在这里配置才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/**")     //拦截全部
                .excludePathPatterns("/user/login", "/user/getLoginPage", "static/**");     //排除部分

    }


}
