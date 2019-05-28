package com.inc.jmosprograms.multidimension.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class RunningCommands {
	public static final String CONTINUA = "CONTINUA";
	public static final String DIFF = "DIFF";
	public static final String DIFFHISTO = "DIFFHISTO";
	public static final String HISTOGRAM = "HISTOGRAM";
	public static final String PLOT = "PLOT";

	private static Logger LOG = Logger.getLogger(RunningCommands.class);

	void executeCommands(String commandType) {
		String param = "";
		if (commandType.equals(CONTINUA)) {
			param = "C:\\jmNewDevelopment\\development-R\\instant-graphs\\scriptR-continua-result.R";
		}
		if (commandType.equals(DIFF)) {
			param = "C:\\jmNewDevelopment\\development-R\\instant-graphs\\scriptR-diff-result.R";
		}
		if (commandType.equals(DIFFHISTO)) {
			param = "C:\\jmNewDevelopment\\development-R\\instant-graphs\\scriptR-diffhisto-result.R";
		}
		if (commandType.equals(HISTOGRAM)) {
			param = "C:\\jmNewDevelopment\\development-R\\instant-graphs\\scriptR-Histogram-result.R";
		}
		if (commandType.equals(PLOT)) {
			param = "C:\\jmNewDevelopment\\development-R\\instant-graphs\\scriptR-plot-result.R";
		}
		ProcessBuilder builder = new ProcessBuilder("C:\\\\Program Files\\\\R\\\\R-3.5.2\\\\bin\\\\Rscript.exe", param);
		builder.redirectErrorStream(true);
		Process process = null;
		try {
			process = builder.start();
			watch(commandType, process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void watch(String commandType, final Process process) {
		new Thread() {
			@Override
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				try {
					while ((line = input.readLine()) != null) {
						LOG.info(commandType + "" + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
