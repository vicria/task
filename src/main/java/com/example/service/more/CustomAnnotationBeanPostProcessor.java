package com.example.service.more;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CustomAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    private final Map<String, Object> beanMap = new HashMap<>();

    public CustomAnnotationBeanPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(CustomAnnotation.class)) {
            beanMap.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanMap.containsKey(beanName)){
            log.info("last part init Root Service");
        }
        return bean;
    }
}
