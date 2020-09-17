/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.aurelius.navalgame1.util.OSUtil;

public class P2PService {
	
	int status = 0;
	Timer ticker;
	boolean isByteStreamComplete = false;
	byte[] currentBytes;
	
	/**
	 * <code>P2PService</code> constructor.
	 * @param client
	 */
	public P2PService(String client) {
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		};
		ticker = new Timer(100,l);
	}
	
	/**
	 * Update.
	 */
	private void update() {
	
	}
	
	/**
	 * Connect to partner.
	 * @throws java.net.SocketException
	 */
	public void connect() throws java.net.SocketException {
		status = 1;
	}
	
	/**
	 * Disconnect from partner.
	 */
	public void disconnect() {
		if (status != 1)
			return;
		status = 0;
	}
	
	/**
	 * Send bytes to partner.
	 * @param packetId
	 * @param data
	 * @return
	 */
	public boolean sendBytes(byte packetId, byte[] data) {
		return false;
	}
	
	/**
	 * Check for newly received bytes.
	 * @param data
	 * @return
	 */
	public byte checkForNewBytes(byte[] data) {
		if (!isByteStreamComplete) {
			data = null;
			return 0;
		}
		else {
			return 0;
		}
	}
	
	public String encrypt(String message) {
		return OSUtil.xorEncode(message, "RiN7VXKb5hjgWiZltq1xijaTHKrZnJxlEwgnBMyHDETDHrCDILRG4ilZH9Ip5PVtqI9NlXeFnZKlcfDx");
	}
	public String decrypt(String message) {
		return OSUtil.xorDecode(message, "RiN7VXKb5hjgWiZltq1xijaTHKrZnJxlEwgnBMyHDETDHrCDILRG4ilZH9Ip5PVtqI9NlXeFnZKlcfDx");
	}
}
