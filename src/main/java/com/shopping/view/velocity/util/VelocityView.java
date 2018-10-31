package com.shopping.view.velocity.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.tools.VelocityFormatter;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.util.NestedServletException;

import com.shopping.view.velocity.VelocityConfig;

public class VelocityView extends AbstractTemplateView {
    private Map toolAttributes;
    private String velocityFormatterAttribute;
    private String dateToolAttribute;
    private String numberToolAttribute;
    private String encoding;
    private boolean cacheTemplate = false;
    private VelocityEngine velocityEngine;
    private Template template;

    public VelocityView() {
    }

    public void setToolAttributes(Properties toolAttributes) {
        this.toolAttributes = new HashMap(toolAttributes.size());

        String attributeName;
        Class toolClass;
        for(Enumeration attributeNames = toolAttributes.propertyNames(); attributeNames.hasMoreElements(); this.toolAttributes.put(attributeName, toolClass)) {
            attributeName = (String)attributeNames.nextElement();
            String className = toolAttributes.getProperty(attributeName);
            toolClass = null;

            try {
                toolClass = ClassUtils.forName(className, null);
            } catch (ClassNotFoundException var7) {
                throw new IllegalArgumentException("Invalid definition for tool '" + attributeName + "' - tool class not found: " + var7.getMessage());
            }
        }

    }

    public void setVelocityFormatterAttribute(String velocityFormatterAttribute) {
        this.velocityFormatterAttribute = velocityFormatterAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute) {
        this.dateToolAttribute = dateToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute) {
        this.numberToolAttribute = numberToolAttribute;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    protected String getEncoding() {
        return this.encoding;
    }

    public void setCacheTemplate(boolean cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
    }

    protected boolean isCacheTemplate() {
        return this.cacheTemplate;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    protected VelocityEngine getVelocityEngine() {
        return this.velocityEngine;
    }

    protected void initApplicationContext() throws BeansException {
        super.initApplicationContext();
        if (this.getVelocityEngine() == null) {
            this.setVelocityEngine(this.autodetectVelocityEngine());
        }

        this.checkTemplate();
    }

    protected VelocityEngine autodetectVelocityEngine() throws BeansException {
        try {
            VelocityConfig velocityConfig = (VelocityConfig)BeanFactoryUtils.beanOfTypeIncludingAncestors(this.getApplicationContext(), VelocityConfig.class, true, false);
            return velocityConfig.getVelocityEngine();
        } catch (NoSuchBeanDefinitionException var2) {
            throw new ApplicationContextException("Must define a single VelocityConfig bean in this web application context (may be inherited): VelocityConfigurer is the usual implementation. This bean may be given any name.", var2);
        }
    }

    protected void checkTemplate() throws ApplicationContextException {
        try {
            this.template = this.getTemplate();
        } catch (ResourceNotFoundException var2) {
            throw new ApplicationContextException("Cannot find Velocity template for URL [" + this.getUrl() + "]: Did you specify the correct resource loader path?", var2);
        } catch (Exception var3) {
            throw new ApplicationContextException("Could not load Velocity template for URL [" + this.getUrl() + "]", var3);
        }
    }

    protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.exposeHelpers(model, request);
        Context velocityContext = this.createVelocityContext(model, request, response);
        this.exposeHelpers(velocityContext, request, response);
        this.exposeToolAttributes(velocityContext, request);
        this.doRender(velocityContext, response);
    }

    protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
    }

    protected Context createVelocityContext(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.createVelocityContext(model);
    }

    protected Context createVelocityContext(Map model) throws Exception {
        return new VelocityContext(model);
    }

    protected void exposeHelpers(Context velocityContext, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.exposeHelpers(velocityContext, request);
    }

    protected void exposeHelpers(Context velocityContext, HttpServletRequest request) throws Exception {
    }

    protected void exposeToolAttributes(Context velocityContext, HttpServletRequest request) throws Exception {
        if (this.toolAttributes != null) {
            Iterator it = this.toolAttributes.entrySet().iterator();

            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                String attributeName = (String)entry.getKey();
                Class toolClass = (Class)entry.getValue();

                try {
                    Object tool = toolClass.newInstance();
                    this.initTool(tool, velocityContext);
                    velocityContext.put(attributeName, tool);
                } catch (Exception var8) {
                    throw new NestedServletException("Could not instantiate Velocity tool '" + attributeName + "'", var8);
                }
            }
        }

        if (this.velocityFormatterAttribute != null) {
            velocityContext.put(this.velocityFormatterAttribute, new VelocityFormatter(velocityContext));
        }

        if (this.dateToolAttribute != null || this.numberToolAttribute != null) {
            Locale locale = RequestContextUtils.getLocale(request);
            if (this.dateToolAttribute != null) {
                velocityContext.put(this.dateToolAttribute, new VelocityView.LocaleAwareDateTool(locale));
            }

            if (this.numberToolAttribute != null) {
                velocityContext.put(this.numberToolAttribute, new VelocityView.LocaleAwareNumberTool(locale));
            }
        }

    }

    protected void initTool(Object tool, Context velocityContext) throws Exception {
    }

    protected void doRender(Context context, HttpServletResponse response) throws Exception {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Rendering Velocity template [" + this.getUrl() + "] in VelocityView '" + this.getBeanName() + "'");
        }

        this.mergeTemplate(this.getTemplate(), context, response);
    }

    protected Template getTemplate() throws Exception {
        return this.isCacheTemplate() && this.template != null ? this.template : this.getTemplate(this.getUrl());
    }

    protected Template getTemplate(String name) throws Exception {
        return this.getEncoding() != null ? this.getVelocityEngine().getTemplate(name, this.getEncoding()) : this.getVelocityEngine().getTemplate(name);
    }

    protected void mergeTemplate(Template template, Context context, HttpServletResponse response) throws Exception {
        try {
            template.merge(context, response.getWriter());
        } catch (MethodInvocationException var5) {
            throw new NestedServletException("Method invocation failed during rendering of Velocity view with name '" + this.getBeanName() + "': " + var5.getMessage() + "; reference [" + var5.getReferenceName() + "], method '" + var5.getMethodName() + "'", var5.getWrappedThrowable());
        }
    }

    private static class LocaleAwareNumberTool extends NumberTool {
        private final Locale locale;

        private LocaleAwareNumberTool(Locale locale) {
            this.locale = locale;
        }

        public Locale getLocale() {
            return this.locale;
        }
    }

    private static class LocaleAwareDateTool extends DateTool {
        private final Locale locale;

        private LocaleAwareDateTool(Locale locale) {
            this.locale = locale;
        }

        public Locale getLocale() {
            return this.locale;
        }
    }
}