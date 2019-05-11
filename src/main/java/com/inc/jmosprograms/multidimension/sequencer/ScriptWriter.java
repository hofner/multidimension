package com.inc.jmosprograms.multidimension.sequencer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inc.jmosprograms.multidimension.config.ApplicationProperties;
import com.inc.jmosprograms.multidimension.vo.ExpandItem;

@Component
public class ScriptWriter {
	public static final int HISTOGRAMA = 1;
	public static final int PLOT = 2;
	@Autowired
	ApplicationProperties props;

	public String saveRscriptsConsole(ArrayList<ExpandItem> consolaHistoR, int scriptType) {

		String path = props.getProjectRoot();
		StringBuffer sb = new StringBuffer();
		String fileoutput = path + "\\";
		if (scriptType == ScriptWriter.HISTOGRAMA) {
			fileoutput += props.getrConsoleHistogram();
		}
		if (scriptType == ScriptWriter.PLOT) {
			fileoutput += props.getrConsolePlot();
		}

		sb.append("getwd()\n");
		sb.append("setwd(\"C:/jmNewDevelopment/development-R/instant-graphs\")\n");
		sb.append("getwd()\n");
		sb.append("library(ggplot2)\n");

		for (ExpandItem expandItem : consolaHistoR) {
			sb.append(expandItem.getEvaluatedExpression() + "\n");
		}

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileoutput;

	}
}
