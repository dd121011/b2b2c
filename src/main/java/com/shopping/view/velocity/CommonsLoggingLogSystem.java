package com.shopping.view.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

public class CommonsLoggingLogSystem implements LogSystem {
    private static final Log logger;

    public CommonsLoggingLogSystem() {
    }

    public void init(RuntimeServices runtimeServices) {
    }

    public void logVelocityMessage(int type, String msg) {
        switch(type) {
        case 0:
            logger.debug(msg);
            break;
        case 1:
            logger.info(msg);
            break;
        case 2:
            logger.warn(msg);
            break;
        case 3:
            logger.error(msg);
        }

    }

    static {
        logger = LogFactory.getLog(VelocityEngine.class);
    }
}