package com.shopping.view.velocity;


import java.io.IOException;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;

public class VelocityEngineFactoryBean extends VelocityEngineFactory implements FactoryBean, InitializingBean, ResourceLoaderAware {
    private VelocityEngine velocityEngine;

    public VelocityEngineFactoryBean() {
    }

    public void afterPropertiesSet() throws IOException, VelocityException {
        this.velocityEngine = this.createVelocityEngine();
    }

    public Object getObject() {
        return this.velocityEngine;
    }

    public Class getObjectType() {
        return VelocityEngine.class;
    }

    public boolean isSingleton() {
        return true;
    }
}