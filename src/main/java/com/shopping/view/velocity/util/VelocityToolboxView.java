package com.shopping.view.velocity.util;


import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ToolboxManager;
import org.apache.velocity.tools.view.context.ChainedContext;
import org.apache.velocity.tools.view.servlet.ServletToolboxManager;
//import org.apache.velocity.tools.view.tools.ViewTool;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class VelocityToolboxView extends VelocityView {
    private String toolboxConfigLocation;

    public VelocityToolboxView() {
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    protected String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }

    protected Context createVelocityContext(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ChainedContext velocityContext = new ChainedContext(new VelocityContext(model), this.getVelocityEngine(), request, response, this.getServletContext());
        if (this.getToolboxConfigLocation() != null) {
            ToolboxManager toolboxManager = ServletToolboxManager.getInstance(this.getServletContext(), this.getToolboxConfigLocation());
            Map toolboxContext = toolboxManager.getToolbox(velocityContext);
            velocityContext.setToolbox(toolboxContext);
        }

        return velocityContext;
    }

    protected void initTool(Object tool, Context velocityContext) throws Exception {
        if (tool instanceof ViewTool) {
            ((ViewTool)tool).init(velocityContext);
        } else {
            Method initMethod = ClassUtils.getMethodIfAvailable(tool.getClass(), "init", new Class[]{Object.class});
            if (initMethod != null) {
                ReflectionUtils.invokeMethod(initMethod, tool, new Object[]{velocityContext});
            }
        }

    }
}