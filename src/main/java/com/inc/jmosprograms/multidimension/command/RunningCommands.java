package com.inc.jmosprograms.multidimension.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class RunningCommands {
	public static final String RSCRIPT_EXE = "C:\\\\Program Files\\\\R\\\\R-3.5.2\\\\bin\\\\Rscript.exe";
	public static final String CONTINUA = "CONTINUA";
	public static final String DIFF = "DIFF";
	public static final String DIFFHISTO = "DIFFHISTO";
	public static final String HISTOGRAM = "HISTOGRAM";
	public static final String PLOT = "PLOT";

	private static Logger LOG = Logger.getLogger(RunningCommands.class);

	public void executeCommands(String commandType, String pathRscript) {
		ProcessBuilder builder = new ProcessBuilder(RunningCommands.RSCRIPT_EXE, pathRscript);
		builder.redirectErrorStream(true);
		Process process = null;
		try {
			process = builder.start();
			watch(commandType, process);
		} catch (IOException e) {
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
						LOG.info(commandType + "->>" + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private static void watch(final Process process) {
		new Thread() {
			@Override
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				try {
					while ((line = input.readLine()) != null) {
						LOG.info(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(String[] args) {
		// ffmpeg -start_number 3139 -r 2 -f image2 -s 1480x802 -i
		// R1-forContinua-20190531-%d.jpg -vcodec libx264 -crf 25 -pix_fmt
		// yuv420p R1-forContinua-20190531.mp4
		String[] params = "-start_number 3139 -r 2 -f image2 -s 1480x802 -i  C:/jmNewDevelopment/development-R/instant-graphss/R1/R1-forContinua-20190601-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p C:/jmNewDevelopment/development-R/instant-graphs/R1/R1-forContinua-20190601.mp4"
				.split(" ");
		System.out.println(params.length);
		ProcessBuilder builder = new ProcessBuilder("ffmpeg", params[0], params[1], params[2], params[3], params[4],
				params[5], params[6], params[7], params[8], params[9], params[10], params[11], params[12], params[13],
				params[14], params[15], params[16], params[17], params[18]);
		// builder.directory(new
		// File("C:/jmNewDevelopment/development-R/instant-graphss/R1"));
		builder.redirectErrorStream(true);
		Process process = null;
		try {
			process = builder.start();
			watch(process);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
