package com.board.configuration;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;


public class CustomSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	 private static final ThreadLocal<SecurityContext> contextHolder = new InheritableThreadLocal<>();

	    public void clearContext() {
	        contextHolder.remove();
	    }

	    public SecurityContext getContext() {
	        System.out.println("Custom strategy start");
	        SecurityContext ctx = contextHolder.get();

	        if (ctx == null) {
	            ctx = createEmptyContext();
	            contextHolder.set(ctx);
	        }
	        System.out.println("Custom strategy end : " + ctx.getClass());
	        return ctx;
	    }

	    public void setContext(SecurityContext context) {
	        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
	        contextHolder.set(context);
	    }

	    public SecurityContext createEmptyContext() {
	        return new SecurityContextImpl();
	    }
	}
