package com.inc.jmosprograms.multidimension.sequencer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inc.jmosprograms.multidimension.config.ApplicationProperties;
import com.inc.jmosprograms.multidimension.vo.ExpandItem;

@Component
public class PatternExpander {
	@Autowired
	ApplicationProperties props;

	@Autowired
	ScriptReader reader;

	@Autowired
	VariableTableModel model;
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private int latestConcurso;

	public ArrayList<ExpandItem> expandScript(String scriptType) {

		String scriptToExpand = "";
		scriptToExpand = reader.readFile(scriptType);
		if (scriptToExpand.indexOf("@fecha@") >= 0) {
			scriptToExpand = scriptToExpand.replaceAll("@fecha@", "" + sdf.format(new Date()));
		}

		ArrayList<String> variables_ins = extractVariablesFromScript(scriptToExpand);
		Hashtable<String, List<Integer>> groupVariableAndvalues_ins = null;
		String fileNameToExpand = null;
		if (scriptType.equals(ScriptReader.SQL_PATTERN_HISTOGRAMA)) {
			groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_HISTOGRAMA);
			fileNameToExpand = props.getSqlCsvHistograma();
		} else {
			if (scriptType.equals(ScriptReader.SQL_PATTERN_PLOT)) {
				groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_PLOT);
				fileNameToExpand = props.getSqlCsvPlot();
			} else {
				if (scriptType.equals(ScriptReader.SQL_PATTERN_CONTINUA)) {
					groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_CONTINUA);
					fileNameToExpand = props.getSqlCsvContinua();
				} else {
					if (scriptType.equals(ScriptReader.SQL_PATTERN_DIFF)) {
						groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_DIFF);
						fileNameToExpand = props.getSqlCsvDiff();
					} else {
						if (scriptType.equals(ScriptReader.SQL_PATTERN_DIFFHISTO)) {
							groupVariableAndvalues_ins = groupVariableAndvalues(
									ScriptReader.VARIABLES_VALUES_DIFFHISTO);
							fileNameToExpand = props.getSqlCsvDiffhisto();
						}
					}
				}

			}
		}
		if (scriptType.equals(ScriptReader.R_PATTERN_HISTOGRAMA)) {
			groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_HISTOGRAMA);

		} else {
			if (scriptType.equals(ScriptReader.R_PATTERN_PLOT)) {
				groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_PLOT);

			} else {
				if (scriptType.equals(ScriptReader.R_PATTERN_CONTINUA)) {
					groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_CONTINUA);
				} else {
					if (scriptType.equals(ScriptReader.R_PATTERN_DIFF)) {
						groupVariableAndvalues_ins = groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_DIFF);
					} else {
						if (scriptType.equals(ScriptReader.R_PATTERN_DIFFHISTO)) {
							groupVariableAndvalues_ins = groupVariableAndvalues(
									ScriptReader.VARIABLES_VALUES_DIFFHISTO);
						}
					}
				}

			}
		}

		ArrayList<ExpandItem> scriptsExpanded = new ArrayList<>();
		expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, scriptToExpand,
				fileNameToExpand, 0);
		return scriptsExpanded;

	}

	private ArrayList<String> extractVariablesFromScript(String script) {
		ArrayList<String> variables = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(script, "@", true);
		int balance = 0;
		while (st.hasMoreElements()) {
			String token = (String) st.nextElement();
			if (token.equals("@")) {
				if (balance == 0) {
					String varFound = (String) st.nextElement();
					if (!variables.contains(varFound)) {
						variables.add(varFound);
					}
					balance++;
				} else {
					balance--;
				}
			}
		}
		return variables;
	}

	public Hashtable<String, List<Integer>> groupVariableAndvalues(String variableType) {
		model.init(variableType);
		int maxi = model.getRowCount();
		ArrayList<Integer> valuesList = null;
		Hashtable<String, List<Integer>> variableHashValues = new Hashtable<>();
		for (int i = 0; i < maxi; i++) {
			String variableName = (String) model.getValueAt(i, 0);

			String valueExpresion = (String) model.getValueAt(i, 1);

			char[] charArray = "20:30".toCharArray();
			String abuscar = "" + charArray[2];
			if (variableName != null && valueExpresion != null && valueExpresion.contains(abuscar)) {
				String iniValStr = valueExpresion.split(":")[0];
				String finValStr = valueExpresion.split(":")[1];
				int inival = Integer.parseInt(iniValStr);
				int finval = Integer.parseInt(finValStr);
				if (variableName.equals("var2") && finval > 3280) {
					finval = latestConcurso;
				}
				valuesList = new ArrayList<>();
				if (inival < finval) {
					for (int k = inival; k <= finval; ++k) {
						valuesList.add(new Integer(k));
					}
				} else {
					for (int k = finval; k >= inival; --k) {
						valuesList.add(new Integer(k));
					}
				}
				variableHashValues.put(variableName, valuesList);
			}

		}
		return variableHashValues;

	}

	public void expandScriptExpressions(ArrayList<ExpandItem> scriptsExpanded, ArrayList<String> variables_ins,
			Hashtable<String, List<Integer>> groupVariableAndvalues_ins, String segment, String fileToSave, int i) {
		String var_i = variables_ins.get(i);
		List<Integer> vals = groupVariableAndvalues_ins.get(var_i);

		for (int j = 0; j < vals.size(); j++) {
			Integer val_enesimo = vals.get(j);

			String segmentEvaluado = segment.replaceAll("@" + var_i + "@", "" + val_enesimo);
			String fileToSaveEvaluado = null;
			if (fileToSave != null) {
				fileToSaveEvaluado = fileToSave.replaceAll("@" + var_i + "@", "" + val_enesimo);
			}

			if (i < variables_ins.size() - 1) {
				expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, segmentEvaluado,
						fileToSaveEvaluado, i + 1);
			} else {
				ExpandItem item = new ExpandItem();
				item.setEvaluatedExpression(segmentEvaluado);
				if (fileToSaveEvaluado != null) {
					fileToSaveEvaluado = fileToSaveEvaluado.replaceAll("@fecha@", "" + sdf.format(new Date()));
					item.setVariable(fileToSaveEvaluado.substring(0, 2));
				}
				item.setFileNameResultset(fileToSaveEvaluado);

				scriptsExpanded.add(item);
			}
		}
	}

	@Deprecated
	void expandScriptExpressions2(ArrayList<String> scriptsExpanded, ArrayList<String> variables_ins,
			Hashtable<String, List<Integer>> groupVariableAndvalues_ins, String segment, int i) {
		String var_i = variables_ins.get(i);
		List<Integer> vals = groupVariableAndvalues_ins.get(var_i);

		for (int j = 0; j < vals.size(); j++) {
			Integer val_enesimo = vals.get(j);
			String segmentEvaluado = segment.replaceAll("@" + var_i + "@", "" + val_enesimo);
			if (i < variables_ins.size() - 1) {
				expandScriptExpressions2(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, segmentEvaluado,
						i + 1);
			} else {
				scriptsExpanded.add(segmentEvaluado);
			}
		}
	}

	public static void main(String[] args) {
		PatternExpander expander = new PatternExpander();

		ArrayList<ExpandItem> scriptsExpanded = new ArrayList<>();
		ArrayList<String> variables_ins = new ArrayList<>();
		Hashtable<String, List<Integer>> groupVariableAndvalues_ins = new Hashtable<>();
		ExpandItem item = new ExpandItem();

		variables_ins.add("var1");
		variables_ins.add("var2");
		groupVariableAndvalues_ins.put("var1",
				Arrays.stream(new int[] { 1, 2, 3, 4, 5, 6 }).boxed().collect(Collectors.toList()));
		groupVariableAndvalues_ins.put("var2",
				Arrays.stream(new int[] { 2200, 2201, 2202, 2203 }).boxed().collect(Collectors.toList()));

		String content1 = "select R@var1@,CONCURSO,fecha from multidimension "
				+ "where fecha>= str_to_date('1/1/2009','%d/%m/%Y') and "
				+ " fecha< str_to_date('1/1/2020','%d/%m/%Y') " + "and concurso<=@var2@ " + "order by CONCURSO desc;";
		item.setEvaluatedExpression(content1);

		expander.expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, content1,
				"R@var1@-forHistograma-20190412-@var2@.csv", 0);
		for (int i = 0; i < scriptsExpanded.size(); i++) {
			ExpandItem expandItem = scriptsExpanded.get(i);
			System.out.println(expandItem.getEvaluatedExpression() + "\t" + expandItem.getFileNameResultset());

		}
	}

	public void setMaxConcurso(int latestConcurso) {
		this.latestConcurso = latestConcurso;

	}

	public int getLatestConcurso() {
		return latestConcurso;
	}

	public void setLatestConcurso(int latestConcurso) {
		this.latestConcurso = latestConcurso;
	}

}
