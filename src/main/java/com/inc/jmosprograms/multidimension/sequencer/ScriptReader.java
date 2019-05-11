package com.inc.jmosprograms.multidimension.sequencer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class ScriptReader {
	public static final String VARIABLES_VALUES_HISTOGRAMA = "variables-values-histograma.txt";
	public static final String VARIABLES_VALUES_PLOT = "variables-values-plot.txt";
	public static final String R_PATTERN_PLOT = "r-pattern-plot.R";
	public static final String R_PATTERN_HISTOGRAMA = "r-pattern-histograma.R";
	public static final String SQL_PATTERN_PLOT = "sql-pattern-plot.sql";
	public static final String SQL_PATTERN_HISTOGRAMA = "sql-pattern-histograma.sql";

	private String readFile(String filename) {
		InputStream resourceAsStream = ScriptReader.class.getResourceAsStream(filename);
		InputStreamReader ir = new InputStreamReader(resourceAsStream);
		BufferedReader br = new BufferedReader(ir);
		String line = null;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			ir.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	public String loadSQLScriptHistograma() {
		return readFile(SQL_PATTERN_HISTOGRAMA);
	}

	public String loadSQLScriptPlot() {
		return readFile(SQL_PATTERN_PLOT);
	}

	public String loadRScriptHistograma() {
		return readFile(R_PATTERN_HISTOGRAMA);
	}

	public String loadRScriptPlot() {
		return readFile(R_PATTERN_PLOT);
	}

	public String loadVariablesR() {
		return readFile(VARIABLES_VALUES_PLOT);
	}

	public String loadVariablesSql() {
		return readFile(VARIABLES_VALUES_HISTOGRAMA);
	}

}
