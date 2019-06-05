package com.inc.jmosprograms.multidimension.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inc.jmosprograms.multidimension.config.ApplicationProperties;
import com.inc.jmosprograms.multidimension.sequencer.OriginEntityBuilder;
import com.inc.jmosprograms.multidimension.sequencer.PatternExpander;
import com.inc.jmosprograms.multidimension.sequencer.ScriptReader;

@Service
public class RunningCommandsMovies {

	@Autowired
	PatternExpander expander;
	@Autowired
	OriginEntityBuilder rowsLoader;
	@Autowired
	ApplicationProperties props;

	private static final String FFMPEG = "ffmpeg";
	public static final String HISTOGRAM = "-start_number @start@ -r 2 -f image2 -s 1480x802 -i  @projectroot@/R@var1@/R@var1@-forHistograma-@fecha@-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p @projectroot@/R@var1@/R@var1@-forHistograma-@fecha@.mp4";
	public static final String PLOT = "-start_number @start@ -r 2 -f image2 -s 1480x802 -i  @projectroot@/R@var1@/R@var1@-forPlot-@fecha@-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p @projectroot@/R@var1@/R@var1@-forPlot-@fecha@.mp4";
	public static final String CONTINUA = "-start_number @start@ -r 2 -f image2 -s 1480x802 -i  @projectroot@/R1/R1-forContinua-@fecha@-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p @projectroot@/R1/R1-forContinua-@fecha@.mp4";
	public static final String DIFF = "-start_number @start@ -r 2 -f image2 -s 1480x802 -i  @projectroot@/R1/R1-forDiff-@fecha@-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p @projectroot@/R1/R1-forDiff-@fecha@.mp4";
	public static final String DIFFHISTO = "-start_number @start@ -r 2 -f image2 -s 1480x802 -i  @projectroot@/R1/R1-forDiffhisto-@fecha@-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p @projectroot@/R1/R1-forDiffhisto-@fecha@.mp4";

	private static Logger LOG = Logger.getLogger(RunningCommandsMovies.class);

	private static final int TIME_INTERVAL = 24 * 60 * 60 * 1000;

	public void executeCommands(String commandType, String var1) {

		expander.setMaxConcurso(rowsLoader.getLatestConcurso());
		String pars = "";
		if (commandType.equals(RunningCommands.HISTOGRAM)) {
			pars = HISTOGRAM.replaceAll("@fecha@", expander.sdf.format(new Date()));
			pars = pars.replaceAll("@projectroot@", props.getMoviesProjectRoot());
			if (var1 != null) {
				pars = pars.replaceAll("@var1@", var1);
			}
			Hashtable<String, List<Integer>> listValues = expander
					.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_HISTOGRAMA);
			List<Integer> valores1 = listValues.get("var2");
			Integer initval = valores1.get(0);
			pars = pars.replaceAll("@start@", "" + initval);
		}
		if (commandType.equals(RunningCommands.PLOT)) {
			pars = PLOT.replaceAll("@fecha@", expander.sdf.format(new Date()));
			pars = pars.replaceAll("@projectroot@", props.getMoviesProjectRoot());
			if (var1 != null) {
				pars = pars.replaceAll("@var1@", var1);
			}
			Hashtable<String, List<Integer>> listValues = expander
					.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_PLOT);
			Integer initval = listValues.get("var2").get(0);
			pars = pars.replaceAll("@start@", "" + initval);
		}
		if (commandType.equals(RunningCommands.CONTINUA)) {
			pars = CONTINUA.replaceAll("@fecha@", expander.sdf.format(new Date()));
			pars = pars.replaceAll("@projectroot@", props.getMoviesProjectRoot());
			Hashtable<String, List<Integer>> listValues = expander
					.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_CONTINUA);
			Integer initval = listValues.get("var2").get(0);
			pars = pars.replaceAll("@start@", "" + initval);
		}
		if (commandType.equals(RunningCommands.DIFF)) {
			pars = DIFF.replaceAll("@fecha@", expander.sdf.format(new Date()));
			pars = pars.replaceAll("@projectroot@", props.getMoviesProjectRoot());
			Hashtable<String, List<Integer>> listValues = expander
					.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_DIFF);
			Integer initval = listValues.get("var2").get(0);
			pars = pars.replaceAll("@start@", "" + initval);
		}
		if (commandType.equals(RunningCommands.DIFFHISTO)) {
			pars = DIFFHISTO.replaceAll("@fecha@", expander.sdf.format(new Date()));
			pars = pars.replaceAll("@projectroot@", props.getMoviesProjectRoot());
			Hashtable<String, List<Integer>> listValues = expander
					.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_DIFFHISTO);
			Integer initval = listValues.get("var2").get(0);
			pars = pars.replaceAll("@start@", "" + initval);
		}
		String[] params = pars.split(" ");
		ProcessBuilder builder = new ProcessBuilder(RunningCommandsMovies.FFMPEG, params[0], params[1], params[2],
				params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11],
				params[12], params[13], params[14], params[15], params[16], params[17], params[18]);
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

	public static void main(String[] args) {
		// ffmpeg -start_number 3139 -r 2 -f image2 -s 1480x802 -i
		// R1-forContinua-20190531-%d.jpg -vcodec libx264 -crf 25 -pix_fmt
		// yuv420p R1-forContinua-20190531.mp4
		String[] params = "-start_number 3139 -r 2 -f image2 -s 1480x802 -i  C:/jmNewDevelopment/development-R/instant-graphss/R1/R1-forContinua-20190601-%d.jpg -vcodec libx264 -crf 25  -pix_fmt yuv420p C:/jmNewDevelopment/development-R/instant-graphs/R1/R1-forContinua-20190601.mp4"
				.split(" ");
		System.out.println(params.length);
		ProcessBuilder builder = new ProcessBuilder(RunningCommandsMovies.FFMPEG, params[0], params[1], params[2],
				params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11],
				params[12], params[13], params[14], params[15], params[16], params[17], params[18]);
		// builder.directory(new
		// File("C:/jmNewDevelopment/development-R/instant-graphss/R1"));
		builder.redirectErrorStream(true);
		Process process = null;
		try {
			process = builder.start();
			watch("proceso", process);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
