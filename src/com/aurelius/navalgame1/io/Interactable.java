/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.io;

/**
 * A abstract class containing IO methods to help make interaction between the file-system, user mode, and server more efficient and easier.
 */
public abstract interface Interactable {
	
	/**
	 * Saves all the data to a specified file.
	 * @param path The path to the file to save.
	 */
	public abstract void save(String path);
	
	/**
	 * Loads all the data to a specified file.
	 * @param path The path to the file to load.
	 */
	public abstract void load(String path);
	
	/**
	 * Validates elements after a load, or before a save, to make sure proper locks have been acquired, and that all the sub data of the class is valid.
	 */
	public abstract void peekElements();
}
