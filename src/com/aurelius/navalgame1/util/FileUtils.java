/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

public class FileUtils {
	private static File workDir = null;

	/**
	 * Get directory to save files to based on OS.
	 * @return
	 */
	public static File getSavingDirectory() {
		if (workDir == null)
			workDir = getSavingDirectory("navalbattle");
		return workDir;
	}
	
	/**
	 * Finds specific directory based on OS.
	 * @param applicationName
	 * @return
	 */
	public static File getSavingDirectory(String applicationName) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (getPlatform()) {
		case solaris:
		case linux:
			workingDirectory = new File(userHome, '.' + applicationName + '/');
			break;
		case windows:
			String applicationData = System.getenv("APPDATA");
			if (applicationData != null)
				workingDirectory = new File(applicationData, "."
						+ applicationName + '/');
			else
				workingDirectory = new File(userHome,
						'.' + applicationName + '/');
			break;
		case macos:
			workingDirectory = new File(userHome,
					"Library/Application Support/" + applicationName);
			break;
		default:
			workingDirectory = new File(userHome, applicationName + '/');
		}
		if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs()))
			throw new RuntimeException(
					"The working directory could not be created: "
							+ workingDirectory);
		return workingDirectory;
	}
	
	/**
	 * Get resource path.
	 * @param s
	 * @return
	 */
	public static URL getResourcePath(String s){
		return FileUtils.class.getResource("/com/jpii/navalbattle/res/" + s);
	}
	
	/**
	 * Get resource at location.
	 * @param s
	 * @return
	 */
	public static File getResource(String s){
		File f=null;
		try {
			f= new File((getResourcePath(s).toURI()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	/**
	 * Get image at location.
	 * @param s
	 * @return
	 */
	public static BufferedImage getImage(String s){
		BufferedImage i = null;
		try {
			i = ImageIO.read(FileUtils.class.getResource("/com/jpii/navalbattle/res/"+s));
		} catch (Throwable e) {
			System.out.println("Failed to load:" + s + "@" + e.getMessage());
		}
		return i;
	}
	
	/**
	 * Get image from other locations.
	 * @param s
	 * @return
	 */
	public static BufferedImage getImageFromOtherPath(String s) {
		BufferedImage i = null;
		try {
			i = ImageIO.read(FileUtils.class.getResource(s));
		} catch (Throwable e) {
			System.out.println("Failed to load:" + s + "@" + e.getMessage());
		}
		return i;
	}
	
	/**
	 * Get platform.
	 * @return
	 */
	public static OS getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return OS.windows;
		if (osName.contains("mac"))
			return OS.macos;
		if (osName.contains("solaris"))
			return OS.solaris;
		if (osName.contains("sunos"))
			return OS.solaris;
		if (osName.contains("linux"))
			return OS.linux;
		if (osName.contains("unix"))
			return OS.linux;
		return OS.unknown;
	}
	
	/**
	 * Returns if a String is "empty".
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}
	
	/**
	 * Open file browser based on OS.
	 * @param uri
	 */
	public static void openLink(URI uri) {
		try {
			Object o = Class.forName("java.awt.Desktop")
					.getMethod("getDesktop", new Class[0])
					.invoke(null, new Object[0]);
			o.getClass().getMethod("browse", new Class[] { URI.class })
			.invoke(o, new Object[] { uri });
		} catch (Exception e) {
			System.out.println("Failed to open link " + uri.toString());
		}
	}
}