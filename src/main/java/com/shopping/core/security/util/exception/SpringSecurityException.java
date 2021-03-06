/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shopping.core.security.util.exception;

import org.springframework.core.NestedRuntimeException;


/**
 * Abstract superclass for all exceptions thrown in the security package and subpackages.<p>Note that this is a
 * runtime (unchecked) exception. Security exceptions are usually fatal; there is no reason for them to be checked.</p>
 *
 * @author Ben Alex
 * @version $Id: SpringSecurityException.java 2559 2008-01-30 16:15:02Z luke_t $
 */
public abstract class SpringSecurityException extends NestedRuntimeException {
    //~ Constructors ===================================================================================================

    /**
     * Constructs an <code>SpringSecurityException</code> with the specified
     * message and root cause.
     *
     * @param msg the detail message
     * @param t the root cause
     */
    public SpringSecurityException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an <code>SpringSecurityException</code> with the specified
     * message and no root cause.
     *
     * @param msg the detail message
     */
    public SpringSecurityException(String msg) {
        super(msg);
    }
}