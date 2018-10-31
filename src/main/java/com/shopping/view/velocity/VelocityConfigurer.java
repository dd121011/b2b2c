//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shopping.view.velocity;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
//import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.context.ServletContextAware;

public class VelocityConfigurer extends VelocityEngineFactory implements VelocityConfig, InitializingBean, ResourceLoaderAware, ServletContextAware {
    private static final String SPRING_MACRO_RESOURCE_LOADER_NAME = "springMacro";
    private static final String SPRING_MACRO_RESOURCE_LOADER_CLASS = "springMacro.resource.loader.class";
    private static final String SPRING_MACRO_LIBRARY = "org/springframework/web/servlet/view/velocity/spring.vm";
    private VelocityEngine velocityEngine;
    private ServletContext servletContext;

    public VelocityConfigurer() {
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void afterPropertiesSet() throws IOException, VelocityException {
        if (this.velocityEngine == null) {
            this.velocityEngine = this.createVelocityEngine();
        }

    }

    protected void postProcessVelocityEngine(VelocityEngine velocityEngine) {
        velocityEngine.setApplicationAttribute(ServletContext.class.getName(), this.servletContext);
        velocityEngine.setProperty("springMacro.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.addProperty("resource.loader", "springMacro");
        velocityEngine.addProperty("velocimacro.library", "org/springframework/web/servlet/view/velocity/spring.vm");
        if (this.logger.isInfoEnabled()) {
            this.logger.info("ClasspathResourceLoader with name 'springMacro' added to configured VelocityEngine");
        }

    }

    public VelocityEngine getVelocityEngine() {
        return this.velocityEngine;
    }
}