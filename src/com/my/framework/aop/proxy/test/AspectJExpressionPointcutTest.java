package com.my.framework.aop.proxy.test;

import org.junit.Assert;
import org.junit.Test;

import com.my.framework.aop.proxy.AspectJExpressionPointcut;


public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* us.codecraft.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
       // boolean matches = aspectJExpressionPointcut.getClassFilter().matches(BookFacade.class);
       // Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* com.my.framework.aop.proxy.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(BookFacade.class.getDeclaredMethod("addBook"),BookFacadeImpl.class);
        Assert.assertTrue(matches);
    }
}
