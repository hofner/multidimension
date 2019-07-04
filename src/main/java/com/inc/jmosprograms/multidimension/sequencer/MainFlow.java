package com.inc.jmosprograms.multidimension.sequencer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.inc.jmosprograms.multidimension.command.RunningCommands;
import com.inc.jmosprograms.multidimension.command.RunningCommandsMovies;
import com.inc.jmosprograms.multidimension.dao.MultidimensionDAO;
import com.inc.jmosprograms.multidimension.repository.PipelineDAO;
import com.inc.jmosprograms.multidimension.service.ReaderService;
import com.inc.jmosprograms.multidimension.vo.ExpandItem;
import com.inc.jmosprograms.multidimension.vo.MelateVoContainers;

@Service
public class MainFlow {

	@Autowired
	Finder finder;
	@Autowired
	OriginEntityBuilder rowsLoader;
	@Autowired
	PipelineDAO dao;
	@Autowired
	PatternExpander expander;
	@Autowired
	MultidimensionDAO dimensionDAO;
	@Autowired
	ScriptWriter swrit;
	@Autowired
	RunningCommandsMovies runningCommandsMovies;

	private static Logger LOG = Logger.getLogger(ReaderService.class);
	/// interval de 24 horas
	private static final int TIME_INTERVAL = 24 * 60 * 60 * 1000;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(fixedRate = TIME_INTERVAL)
	public void pipelineMultidimension() {
		LOG.info("Corriendolo :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));

		/////////////////////////////////////////
		LOG.info("Downloading URL... - " + dateTimeFormatter.format(LocalDateTime.now()));
		MelateVoContainers melatesContainers = rowsLoader.loadFileUrl();
		LOG.info("Data from URL with data to be analized Done- " + dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("Truncating tables... - " + dateTimeFormatter.format(LocalDateTime.now()));
		dao.truncateTables();
		LOG.info("Tables cleaned Done- " + dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("Saving data into database... " + dateTimeFormatter.format(LocalDateTime.now()));
		dao.saveAll(melatesContainers);
		LOG.info("TERMINADO :: " + melatesContainers.getResult().size() + " inserted ROWS- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
		expander.setMaxConcurso(rowsLoader.getLatestConcurso());
		LOG.info("Generating query scripts... - " + dateTimeFormatter.format(LocalDateTime.now()));
		ArrayList<ExpandItem> sentenciasHistograma = expander.expandScript(ScriptReader.SQL_PATTERN_HISTOGRAMA);
		ArrayList<ExpandItem> sentenciasPlot = expander.expandScript(ScriptReader.SQL_PATTERN_PLOT);
		ArrayList<ExpandItem> sentenciasContinua = expander.expandScript(ScriptReader.SQL_PATTERN_CONTINUA);
		ArrayList<ExpandItem> sentenciasDiff = expander.expandScript(ScriptReader.SQL_PATTERN_DIFF);
		ArrayList<ExpandItem> sentenciasDiffhisto = expander.expandScript(ScriptReader.SQL_PATTERN_DIFFHISTO);
		ArrayList<ExpandItem> sentenciasWeka = expander.expandScriptWeka(ScriptReader.SQL_PATTERN_WEKA);
		LOG.info("Querys generated - " + dateTimeFormatter.format(LocalDateTime.now()));

		try {
			LOG.info("Excuting queries and saving them... - " + dateTimeFormatter.format(LocalDateTime.now()));
			dimensionDAO.executeAndSaveResultset(sentenciasHistograma);
			dimensionDAO.executeAndSaveResultset(sentenciasPlot);
			dimensionDAO.executeAndSaveResultset(sentenciasContinua);
			dimensionDAO.executeAndSaveResultset(sentenciasDiff);
			dimensionDAO.executeAndSaveResultset(sentenciasDiffhisto);
			dimensionDAO.executeAndSaveResultset(sentenciasWeka);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		LOG.info("Querys executed and results saved (Histograma & Plot)- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("Generating and expanding R scripts...- " + dateTimeFormatter.format(LocalDateTime.now()));
		ArrayList<ExpandItem> consolaHistoR = expander.expandScript(ScriptReader.R_PATTERN_HISTOGRAMA);
		ArrayList<ExpandItem> consolaPlotR = expander.expandScript(ScriptReader.R_PATTERN_PLOT);
		ArrayList<ExpandItem> consolaContinuaR = expander.expandScript(ScriptReader.R_PATTERN_CONTINUA);
		ArrayList<ExpandItem> consolaDiffR = expander.expandScript(ScriptReader.R_PATTERN_DIFF);
		ArrayList<ExpandItem> consolaDiffhistoR = expander.expandScript(ScriptReader.R_PATTERN_DIFFHISTO);
		LOG.info("R scripts expanded - " + dateTimeFormatter.format(LocalDateTime.now()));

		LOG.info("Saving R scripts...- " + dateTimeFormatter.format(LocalDateTime.now()));
		String fileRForR_API_Histograma = swrit.saveRscriptsConsole(consolaHistoR, ScriptWriter.HISTOGRAMA);
		String fileRForR_API_Plot = swrit.saveRscriptsConsole(consolaPlotR, ScriptWriter.PLOT);
		String fileRForR_API_Continua = swrit.saveRscriptsConsole(consolaContinuaR, ScriptWriter.CONTINUA);
		String fileRForR_API_Diff = swrit.saveRscriptsConsole(consolaDiffR, ScriptWriter.DIFF);
		String fileRForR_API_Diffhisto = swrit.saveRscriptsConsole(consolaDiffhistoR, ScriptWriter.DIFFHISTO);
		LOG.info("R scripts saved in folder to be run - " + dateTimeFormatter.format(LocalDateTime.now()));

		LOG.info("Runing scripts in R engine - " + dateTimeFormatter.format(LocalDateTime.now()));
		// vamos a correr los archivos r generados con el engine de R
		RunningCommands commands = new RunningCommands();
		commands.executeCommands(RunningCommands.HISTOGRAM, fileRForR_API_Histograma);
		commands.executeCommands(RunningCommands.PLOT, fileRForR_API_Plot);
		commands.executeCommands(RunningCommands.CONTINUA, fileRForR_API_Continua);
		commands.executeCommands(RunningCommands.DIFF, fileRForR_API_Diff);
		commands.executeCommands(RunningCommands.DIFFHISTO, fileRForR_API_Diffhisto);

		LOG.info("SUCCESS");

		LOG.info("scripts finished - " + dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("PIPELINE finished - " + dateTimeFormatter.format(LocalDateTime.now()));

		////////////////////////////

	}

	// @Scheduled(fixedRate = TIME_INTERVAL)
	public void moviesMultidimension() {
		LOG.info("Corriendolo :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));
		MelateVoContainers melatesContainers = rowsLoader.loadFileUrl();
		expander.setMaxConcurso(rowsLoader.getLatestConcurso());
		/////////////////////////////////////////

		for (int i = 1; i <= 6; i++) {
			runningCommandsMovies.executeCommands(RunningCommands.HISTOGRAM, "" + i);
			runningCommandsMovies.executeCommands(RunningCommands.PLOT, "" + i);
		}
		runningCommandsMovies.executeCommands(RunningCommands.CONTINUA, null);
		runningCommandsMovies.executeCommands(RunningCommands.DIFF, null);
		runningCommandsMovies.executeCommands(RunningCommands.DIFFHISTO, null);

	}

}
