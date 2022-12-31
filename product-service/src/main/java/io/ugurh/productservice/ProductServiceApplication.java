package io.ugurh.productservice;

import io.ugurh.productservice.core.errorhandling.ProductServiceEventErrorHandler;
import io.ugurh.productservice.interceptor.CreateProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerCreatedProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
        CreateProductCommandInterceptor bean = context.getBean(CreateProductCommandInterceptor.class);
        commandBus.registerDispatchInterceptor(bean);
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler("product-group",
                configuration -> new ProductServiceEventErrorHandler());

//        configurer.registerListenerInvocationErrorHandler("product-group",
//                configuration -> PropagatingErrorHandler.instance());

    }

}
