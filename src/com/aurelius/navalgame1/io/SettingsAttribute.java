/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.io;

public class SettingsAttribute {
	
	String name, value;
	
	/**
	 * <code>SettingsAttribute</code> constructor.
	 * @param name	Name of attribute.
	 */
	public SettingsAttribute(String name) {
		this.name = name;
		value = "";
	}
	
	/**
	 * <code>SettingsAttribute</code> constructor.
	 * @param name		Name of attribute.
	 * @param value		Value of attribute.
	 */
	public SettingsAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns name of attribute.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns value of attribute.
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set name of attribute.
	 * @param n
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Set value of attribute.
	 * @param val
	 */
	public void setValue(String val) {
		value = val;
	}
	
	/**
	 * Return attribute in a readable format.
	 */
	public String toString() {
		return name + ": " + value;
	}
}
