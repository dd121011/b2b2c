package com.shopping.view.velocity.util;

/**
 * Generic view tool interface to assist in tool management.
 * This interface provides the {@link #init(Object initData)} method 
 * as a hook for ToolboxManager implementations to pass data in to
 * tools to initialize them.  See 
 * {@link org.apache.velocity.tools.view.ViewToolInfo} for more on this.
 *
 * @author <a href="mailto:nathan@esha.com">Nathan Bubna</a>
 *
 * @version $Id: ViewTool.java 71982 2004-02-18 20:11:07Z nbubna $
 */
public interface ViewTool
{

    /**
     * Initializes this instance using the given data
     *
     * @param initData the initialization data 
     */
    public void init(Object initData);


}
