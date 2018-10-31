//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shopping.view.velocity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

public class VelocityEngineFactory {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private Resource configLocation;
    private final Map velocityProperties = new HashMap();
    private String resourceLoaderPath;
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private boolean preferFileSystemAccess = true;
    private boolean overrideLogging = true;

    public VelocityEngineFactory() {
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public void setVelocityProperties(Properties velocityProperties) {
        this.setVelocityPropertiesMap(velocityProperties);
    }

    public void setVelocityPropertiesMap(Map velocityPropertiesMap) {
        if (velocityPropertiesMap != null) {
            this.velocityProperties.putAll(velocityPropertiesMap);
        }

    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        this.resourceLoaderPath = resourceLoaderPath;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    protected ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    protected boolean isPreferFileSystemAccess() {
        return this.preferFileSystemAccess;
    }

    public void setOverrideLogging(boolean overrideLogging) {
        this.overrideLogging = overrideLogging;
    }

    public VelocityEngine createVelocityEngine() throws IOException, VelocityException {
        VelocityEngine velocityEngine = this.newVelocityEngine();
        Properties props = new Properties();
        if (this.configLocation != null) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Loading Velocity config from [" + this.configLocation + "]");
            }

            PropertiesLoaderUtils.fillProperties(props, this.configLocation);
        }

        if (!this.velocityProperties.isEmpty()) {
            props.putAll(this.velocityProperties);
        }

        if (this.resourceLoaderPath != null) {
            this.initVelocityResourceLoader(velocityEngine, this.resourceLoaderPath);
        }

        if (this.overrideLogging) {
            velocityEngine.setProperty("runtime.log.logsystem", new CommonsLoggingLogSystem());
        }

        Iterator it = props.entrySet().iterator();

        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Illegal property key [" + entry.getKey() + "]: only Strings allowed");
            }

            velocityEngine.setProperty((String)entry.getKey(), entry.getValue());
        }

        this.postProcessVelocityEngine(velocityEngine);

        try {
            velocityEngine.init();
            return velocityEngine;
        } catch (VelocityException var6) {
            throw var6;
        } catch (RuntimeException var7) {
            throw var7;
        } catch (Exception var8) {
            this.logger.error("Why does VelocityEngine throw a generic checked exception, after all?", var8);
            throw new VelocityException(var8.toString());
        }
    }

    protected VelocityEngine newVelocityEngine() throws IOException, VelocityException {
        return new VelocityEngine();
    }

    protected void initVelocityResourceLoader(VelocityEngine velocityEngine, String resourceLoaderPath) {
        if (this.isPreferFileSystemAccess()) {
            try {
                StringBuffer resolvedPath = new StringBuffer();
                String[] paths = StringUtils.commaDelimitedListToStringArray(resourceLoaderPath);

                for(int i = 0; i < paths.length; ++i) {
                    String path = paths[i];
                    Resource resource = this.getResourceLoader().getResource(path);
                    File file = resource.getFile();
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("Resource loader path [" + path + "] resolved to file [" + file.getAbsolutePath() + "]");
                    }

                    resolvedPath.append(file.getAbsolutePath());
                    if (i < paths.length - 1) {
                        resolvedPath.append(',');
                    }
                }

                velocityEngine.setProperty("resource.loader", "file");
                velocityEngine.setProperty("file.resource.loader.cache", "true");
                velocityEngine.setProperty("file.resource.loader.path", resolvedPath.toString());
            } catch (IOException var9) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Cannot resolve resource loader path [" + resourceLoaderPath + "] to [java.io.File]: using SpringResourceLoader", var9);
                }

                this.initSpringResourceLoader(velocityEngine, resourceLoaderPath);
            }
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("File system access not preferred: using SpringResourceLoader");
            }

            this.initSpringResourceLoader(velocityEngine, resourceLoaderPath);
        }

    }

    protected void initSpringResourceLoader(VelocityEngine velocityEngine, String resourceLoaderPath) {
        velocityEngine.setProperty("resource.loader", "spring");
        velocityEngine.setProperty("spring.resource.loader.class", SpringResourceLoader.class.getName());
        velocityEngine.setProperty("spring.resource.loader.cache", "true");
        velocityEngine.setApplicationAttribute("spring.resource.loader", this.getResourceLoader());
        velocityEngine.setApplicationAttribute("spring.resource.loader.path", resourceLoaderPath);
    }

    protected void postProcessVelocityEngine(VelocityEngine velocityEngine) throws IOException, VelocityException {
    }
}