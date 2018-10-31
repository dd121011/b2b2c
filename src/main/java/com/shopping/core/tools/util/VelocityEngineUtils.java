//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shopping.core.tools.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

public abstract class VelocityEngineUtils {
    private static final Log logger;

    public VelocityEngineUtils() {
    }

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, Map model, Writer writer) throws VelocityException {
        try {
            VelocityContext velocityContext = new VelocityContext(model);
            velocityEngine.mergeTemplate(templateLocation, velocityContext, writer);
        } catch (VelocityException var5) {
            throw var5;
        } catch (RuntimeException var6) {
            throw var6;
        } catch (Exception var7) {
            logger.error("Why does VelocityEngine throw a generic checked exception, after all?", var7);
            throw new VelocityException(var7.toString());
        }
    }

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, String encoding, Map model, Writer writer) throws VelocityException {
        try {
            VelocityContext velocityContext = new VelocityContext(model);
            velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, writer);
        } catch (VelocityException var6) {
            throw var6;
        } catch (RuntimeException var7) {
            throw var7;
        } catch (Exception var8) {
            logger.error("Why does VelocityEngine throw a generic checked exception, after all?", var8);
            throw new VelocityException(var8.toString());
        }
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, Map model) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, model, result);
        return result.toString();
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, String encoding, Map model) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
        return result.toString();
    }

    static {
        logger = LogFactory.getLog(VelocityEngineUtils.class);
    }
}