package com.inc.jmosprograms.multidimension.sequencer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

public class RExecutor extends Thread {

	private static Logger LOG = Logger.getLogger(RExecutor.class);
	private String scriptName;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public RExecutor(String scriptName) {
		this.scriptName = scriptName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		Runtime rt = Runtime.getRuntime();
		try {
			String param = "\"C:\\Program Files\\R\\R-3.5.2\\bin\\Rscript.exe\" " + scriptName;
			LOG.info(param + " - " + dateTimeFormatter.format(LocalDateTime.now()));
			Process pr = rt.exec(param);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
