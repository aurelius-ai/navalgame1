package com.aurelius.navalgame1.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.aurelius.navalgame1.NavalBattle;

public class HookStream extends PrintStream {

	/**
	 * @param out
	 */
	public HookStream(OutputStream out) {
		super(out);
	}


	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public HookStream(File file) throws FileNotFoundException {
		super(file);
	}

	/**
	 * @param out
	 * @param autoFlush
	 */
	public HookStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	
	public void println(String s) {
		super.println(s);
		
		if (NavalBattle.getDebugWindow() != null) {
			NavalBattle.getDebugWindow().printInfo(s);
		}
	}

}
