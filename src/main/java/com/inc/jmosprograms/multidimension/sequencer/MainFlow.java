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

	private static Logger LOG = Logger.getLogger(ReaderService.class);
	private static final int TIME_INTERVAL = 6 * 60 * 60 * 1000;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(fixedRate = TIME_INTERVAL)
	public void pipelineMultidimension() {
		LOG.info("Corriendolo :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));

		/////////////////////////////////////////
		String filePath = finder.findFileToProcess();
		MelateVoContainers melatesContainers = rowsLoader.loadFile(filePath);
		dao.truncateTables();
		// dao.deleteEntity(Melate.class);
		// dao.deleteEntity(MelateContinua.class);
		dao.saveAll(melatesContainers);

		finder.moveFoundFile();
		ArrayList<String> sentenciasHistograma = expander.expandScript(ScriptReader.SQL_PATTERN_HISTOGRAMA);
		ArrayList<String> sentenciasPlot = expander.expandScript(ScriptReader.SQL_PATTERN_PLOT);
		try {
			ArrayList<String> histogramasCsv = dimensionDAO.findAllMultidimensionResultset(sentenciasHistograma);
			ArrayList<String> plotsCsv = dimensionDAO.findAllMultidimensionResultset(sentenciasPlot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		////////////////////////////

		LOG.info("TERMINADO :: " + melatesContainers.getResult().size() + " INSERTED MELATE and MELATESCONTINUAS ROWS- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
	}

}
