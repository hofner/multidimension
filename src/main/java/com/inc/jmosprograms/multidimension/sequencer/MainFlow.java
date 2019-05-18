package com.inc.jmosprograms.multidimension.sequencer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

	private static Logger LOG = Logger.getLogger(ReaderService.class);
	private static final int TIME_INTERVAL = 6 * 60 * 60 * 1000;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(fixedRate = TIME_INTERVAL)
	public void pipelineMultidimension() {
		LOG.info("Corriendolo :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));

		/////////////////////////////////////////
		MelateVoContainers melatesContainers = rowsLoader.loadFileUrl();
		LOG.info("Data from URL with data to be analized Done- " + dateTimeFormatter.format(LocalDateTime.now()));
		dao.truncateTables();
		LOG.info("Tables cleaned Done- " + dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("Reading URL with data to be analized - " + dateTimeFormatter.format(LocalDateTime.now()));
		dao.saveAll(melatesContainers);
		LOG.info("TERMINADO :: " + melatesContainers.getResult().size() + " INSERTED MELATE and MELATESCONTINUAS ROWS- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("Data from url store in database tables Done- " + dateTimeFormatter.format(LocalDateTime.now()));

		ArrayList<ExpandItem> sentenciasHistograma = expander.expandScript(ScriptReader.SQL_PATTERN_HISTOGRAMA);
		ArrayList<ExpandItem> sentenciasPlot = expander.expandScript(ScriptReader.SQL_PATTERN_PLOT);
		ArrayList<ExpandItem> sentenciasContinua = expander.expandScript(ScriptReader.SQL_PATTERN_CONTINUA);
		LOG.info("Querys generated - " + dateTimeFormatter.format(LocalDateTime.now()));

		try {
			dimensionDAO.executeAndSaveResultset(sentenciasHistograma);
			dimensionDAO.executeAndSaveResultset(sentenciasPlot);
			dimensionDAO.executeAndSaveResultset(sentenciasContinua);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("Querys executed and results saved (Histograma & Plot)- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
		ArrayList<ExpandItem> consolaHistoR = expander.expandScript(ScriptReader.R_PATTERN_HISTOGRAMA);
		ArrayList<ExpandItem> consolaPlotR = expander.expandScript(ScriptReader.R_PATTERN_PLOT);
		ArrayList<ExpandItem> consolaContinuaR = expander.expandScript(ScriptReader.R_PATTERN_CONTINUA);
		LOG.info("R scripts expanded - " + dateTimeFormatter.format(LocalDateTime.now()));
		String fileRForR_API_Histograma = swrit.saveRscriptsConsole(consolaHistoR, ScriptWriter.HISTOGRAMA);
		String fileRForR_API_Plot = swrit.saveRscriptsConsole(consolaPlotR, ScriptWriter.PLOT);
		String fileRForR_API_Continua = swrit.saveRscriptsConsole(consolaContinuaR, ScriptWriter.CONTINUA);
		LOG.info("R scripts save in folder to be run - " + dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("SUCCESS");
		// vamos a correr los archivos r generados con el engine de R
		// la siguiente ejecucion se hace por medio de bats
		/*
		 * RExecutor rExecutor1 = new RExecutor(fileRForR_API_Histograma);
		 * RExecutor rExecutor2 = new RExecutor(fileRForR_API_Plot);
		 * LOG.info("Starting scripts in R engine - " +
		 * dateTimeFormatter.format(LocalDateTime.now())); rExecutor1.start();
		 * rExecutor2.start(); LOG.info("scripts finished - " +
		 * dateTimeFormatter.format(LocalDateTime.now()));
		 * LOG.info("PIPELINE finished - " +
		 * dateTimeFormatter.format(LocalDateTime.now()));
		 */

		////////////////////////////

	}

}
