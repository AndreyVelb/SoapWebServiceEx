package com.velb.soapwebserviceex.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/my-bank/*");
    }


    @Bean(name = "application")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema myExampleSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MyExamplePort");
        wsdl11Definition.setLocationUri("/my-bank");
        wsdl11Definition.setTargetNamespace("http://www.myexample.com");
        wsdl11Definition.setSchema(myExampleSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema myExampleSchema() {
        return new SimpleXsdSchema(new ClassPathResource("myexample.xsd"));
    }

}
