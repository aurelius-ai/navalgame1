/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.navalgame.rauth;

public enum AuthStatus {
	/**
	 * Authentication was successful.
	 */
	GOOD,
	
	/**
	 * Authentication failed.
	 */
	BAD,
	
	/**
	 * Server is offline (API is disabled, but the server could be found).
	 */
	OFFLINE,
	
	/**
	 * Invalid API key
	 */
	INVALID_API_KEY,
	
	/**
	 * Something unknown went wrong. This includes lack of network connectivity.
	 */
	UNKNOWN;
}
