package com.my.framework.init;

public class ManagedBeanContextUtils {
	private static ManagedBeanHandler managedBeanHandler;
	
	static {
		managedBeanHandler = new ManagedBeanHandler();
		managedBeanHandler.initManagedBeanContext();
	}
	
	public synchronized static void init() {
		System.out.println("InitFactory.init()");
	}
	
	public synchronized static ManagedBeanHandler managedBeanHandler() {
		return managedBeanHandler;
	}
}
