package com.inc.jmosprograms.multidimension.sequencer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class ScriptReader {
	public static final String VARIABLES_VALUES_HISTOGRAMA = "variables-values-histograma.txt";
	public static final String VARIABLES_VALUES_PLOT = "variables-values-plot.txt";
	public static final String VARIABLES_VALUES_CONTINUA = "variables-values-continua.txt";
	public static final String VARIABLES_VALUES_DIFF = "variables-values-diff.txt";
	public static final String VARIABLES_VALUES_DIFFHISTO = "variables-values-diffhisto.txt";
	public static final String R_PATTERN_PLOT = "r-pattern-plot.R";
	public static final String R_PATTERN_HISTOGRAMA = "r-pattern-histograma.R";
	public static final String R_PATTERN_CONTINUA = "r-pattern-continua.R";
	public static final String R_PATTERN_DIFF = "r-pattern-diff.R";
	public static final String R_PATTERN_DIFFHISTO = "r-pattern-diffhisto.R";

	public static final String SQL_PATTERN_PLOT = "sql-pattern-plot.sql";
	public static final String SQL_PATTERN_HISTOGRAMA = "sql-pattern-histograma.sql";
	public static final String SQL_PATTERN_CONTINUA = "sql-pattern-continua.sql";
	public static final String SQL_PATTERN_DIFF = "sql-pattern-diff.sql";
	public static final String SQL_PATTERN_DIFFHISTO = "sql-pattern-diffhisto.sql";
	public static final String SQL_PATTERN_WEKA = "sql-pattern-weka.sql";

	public String readFile(String filename) {
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
}
