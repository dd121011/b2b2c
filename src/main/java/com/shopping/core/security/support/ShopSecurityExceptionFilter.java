package com.shopping.core.security.support;
 
 import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.SpringSecurityException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.ui.FilterChainOrder;
//import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
//import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.util.Assert;

import com.shopping.core.security.util.exception.SpringSecurityException;
import com.shopping.core.security.util.filter.FilterChainOrder;
import com.shopping.core.security.util.filter.SpringSecurityFilter;
 
 public class ShopSecurityExceptionFilter extends SpringSecurityFilter
   implements InitializingBean
 {
   private AccessDeniedHandler accessDeniedHandler;
   private AuthenticationEntryPoint authenticationEntryPoint;
   private AuthenticationTrustResolver authenticationTrustResolver;
   private PortResolver portResolver;
   private ThrowableAnalyzer throwableAnalyzer;
   private boolean createSessionAllowed;
 
   public ShopSecurityExceptionFilter()
   {
     this.accessDeniedHandler = new ShopAccessDeniedHandlerImpl();
     this.authenticationTrustResolver = new AuthenticationTrustResolverImpl();
     this.portResolver = new PortResolverImpl();
     this.throwableAnalyzer = new ThrowableAnalyzer();
     this.createSessionAllowed = true;
   }
 
   public void afterPropertiesSet() throws Exception {
     Assert.notNull(this.authenticationEntryPoint, 
       "authenticationEntryPoint must be specified");
     Assert.notNull(this.portResolver, "portResolver must be specified");
     Assert.notNull(this.authenticationTrustResolver, 
       "authenticationTrustResolver must be specified");
     Assert.notNull(this.throwableAnalyzer, 
       "throwableAnalyzer must be specified");
   }
 
   public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
   {
     try
     {
       chain.doFilter(request, response);
 
       if (this.logger.isDebugEnabled())
         this.logger.debug("Chain processed normally");
     } catch (IOException ex) {
       throw ex;
     } catch (Exception ex) {
       Throwable[] causeChain = this.throwableAnalyzer
         .determineCauseChain(ex);
       SpringSecurityException ase = (SpringSecurityException)this.throwableAnalyzer
         .getFirstThrowableOfType(SpringSecurityException.class, 
         causeChain);
 
       if (ase != null) {
         handleException(request, response, chain, ase);
       } else {
         if ((ex instanceof ServletException)) {
           throw ((ServletException)ex);
         }
         if ((ex instanceof RuntimeException)) {
           throw ((RuntimeException)ex);
         }
 
         throw new RuntimeException(ex);
       }
     }
   }
 
   public AuthenticationEntryPoint getAuthenticationEntryPoint() {
     return this.authenticationEntryPoint;
   }
 
   public AuthenticationTrustResolver getAuthenticationTrustResolver() {
     return this.authenticationTrustResolver;
   }
 
   public PortResolver getPortResolver() {
     return this.portResolver;
   }
 
//   private void handleException(ServletRequest request, ServletResponse response, FilterChain chain, SpringSecurityException exception)
   private void handleException(ServletRequest request, ServletResponse response, FilterChain chain, Exception exception)
     throws IOException, ServletException
   {
     if ((exception instanceof AuthenticationException)) {
//       if (this.logger.isDebugEnabled()) {
//         this.logger
//           .debug(
//           "Authentication exception occurred; redirecting to authentication entry point", 
//           exception);
//       }
 
       sendStartAuthentication(request, response, chain, (AuthenticationException)exception);
     } else if ((exception instanceof AccessDeniedException))
     {
       if (this.authenticationTrustResolver
         .isAnonymous(SecurityContextHolder.getContext()
         .getAuthentication())) {
         if (this.logger.isDebugEnabled()) {
           this.logger
             .debug(
             "Access is denied (user is anonymous); redirecting to authentication entry point", 
             exception);
         }
 
         sendStartAuthentication(
           request, 
           response, 
           chain, 
           new InsufficientAuthenticationException(
           "Full authentication is required to access this resource"));
       } else {
         if (this.logger.isDebugEnabled()) {
           this.logger
             .debug(
             "Access is denied (user is not anonymous); delegating to AccessDeniedHandler", 
             exception);
         }
 
//         this.accessDeniedHandler.handle(request, response,exception);
       }
     }
   }
 
   public boolean isCreateSessionAllowed() {
     return this.createSessionAllowed;
   }
 
//   protected void sendStartAuthentication(ServletRequest request, ServletResponse response, FilterChain chain, AuthenticationException reason)
//     throws ServletException, IOException
     protected void sendStartAuthentication(ServletRequest request, ServletResponse response, FilterChain chain, AuthenticationException reason)
    		 throws ServletException, IOException
   {
     HttpServletRequest httpRequest = (HttpServletRequest)request;
 
     DefaultSavedRequest savedRequest = new DefaultSavedRequest(httpRequest,this.portResolver);
 
     if (this.logger.isDebugEnabled()) {
       this.logger
         .debug("Authentication entry point being called; SavedRequest added to Session: " + 
         savedRequest);
     }
 
     if (this.createSessionAllowed) {
       httpRequest.getSession().setAttribute(
         "SPRING_SECURITY_SAVED_REQUEST_KEY", savedRequest);
     }
 
     SecurityContextHolder.getContext().setAuthentication(null);
 
     this.authenticationEntryPoint.commence(httpRequest, (HttpServletResponse) response, reason);
   }
 
   public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
     Assert.notNull(accessDeniedHandler, "AccessDeniedHandler required");
     this.accessDeniedHandler = accessDeniedHandler;
   }
 
   public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint)
   {
     this.authenticationEntryPoint = authenticationEntryPoint;
   }
 
   public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver)
   {
     this.authenticationTrustResolver = authenticationTrustResolver;
   }
 
   public void setCreateSessionAllowed(boolean createSessionAllowed) {
     this.createSessionAllowed = createSessionAllowed;
   }
 
   public void setPortResolver(PortResolver portResolver) {
     this.portResolver = portResolver;
   }
 
   public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
     this.throwableAnalyzer = throwableAnalyzer;
   }
 
   public int getOrder() {
     return FilterChainOrder.EXCEPTION_TRANSLATION_FILTER;
   }
 }