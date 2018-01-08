package com.rsvier.workshop1.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Logger {
	
	private static Logger logger;
	
	private Logger() throws IOException {
		logger = LoggerFactory.getLogger(Logger.class);
	}
	
	public static Logger getLogger() {
		try {
			new Logger();
		} catch(IOException ex) {
			//TODO add code
		}
		return logger;
	}
}
