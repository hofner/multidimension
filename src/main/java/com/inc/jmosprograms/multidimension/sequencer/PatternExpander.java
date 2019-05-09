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

import com.inc.jmosprograms.multidimension.vo.ExpandItem;

@Component
public class PatternExpander {
	@Autowired
	ScriptReader reader;

	public ArrayList<String> expandScript(String scriptType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String scriptToExpand = "";
		if (scriptType.equals(ScriptReader.SQL_PATTERN_HISTOGRAMA)) {
			scriptToExpand = reader.loadSQLScriptHistograma();
		}
		if (scriptType.equals(ScriptReader.SQL_PATTERN_PLOT)) {
			scriptToExpand = reader.loadSQLScriptPlot();
		}
		if (scriptType.equals(ScriptReader.R_PATTERN_HISTOGRAMA)) {
			scriptToExpand = reader.loadRScriptHistograma();
		}
		if (scriptType.equals(ScriptReader.R_PATTERN_PLOT)) {
			scriptToExpand = reader.loadRScriptPlot();
		}
		if (scriptToExpand.indexOf("@fecha@") >= 0) {
			scriptToExpand = scriptToExpand.replaceAll("@fecha@", "" + sdf.format(new Date()));
		}

		ArrayList<String> variables_ins = this.extractVariablesFromScript(scriptToExpand);
		Hashtable<String, List<Integer>> groupVariableAndvalues_ins = null;
		if (scriptType.equals(ScriptReader.SQL_PATTERN_HISTOGRAMA)
				|| scriptType.equals(ScriptReader.SQL_PATTERN_PLOT)) {
			groupVariableAndvalues_ins = this.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_SQL);
		}
		if (scriptType.equals(ScriptReader.R_PATTERN_HISTOGRAMA) || scriptType.equals(ScriptReader.R_PATTERN_PLOT)) {
			groupVariableAndvalues_ins = this.groupVariableAndvalues(ScriptReader.VARIABLES_VALUES_R);
		}
		ArrayList<String> scriptsExpanded = new ArrayList<>();
		this.expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, scriptToExpand, 0);
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

	private Hashtable<String, List<Integer>> groupVariableAndvalues(String variableType) {
		VariableTableModel model = new VariableTableModel(variableType);
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
			Hashtable<String, List<Integer>> groupVariableAndvalues_ins, ExpandItem item, int i) {
		String var_i = variables_ins.get(i);
		List<Integer> vals = groupVariableAndvalues_ins.get(var_i);

		for (int j = 0; j < vals.size(); j++) {
			Integer val_enesimo = vals.get(j);

			String segmentEvaluado = item.getEvaluatedExpression().replaceAll("@" + var_i + "@", "" + val_enesimo);
			item.setEvaluatedExpression(segmentEvaluado);
			item.setVariable(var_i);
			if (i < variables_ins.size() - 1) {
				expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, item, i + 1);
			} else {
				scriptsExpanded.add(item);
			}
		}
	}

	@Deprecated
	void expandScriptExpressions(ArrayList<String> scriptsExpanded, ArrayList<String> variables_ins,
			Hashtable<String, List<Integer>> groupVariableAndvalues_ins, String segment, int i) {
		String var_i = variables_ins.get(i);
		List<Integer> vals = groupVariableAndvalues_ins.get(var_i);

		for (int j = 0; j < vals.size(); j++) {
			Integer val_enesimo = vals.get(j);
			String segmentEvaluado = segment.replaceAll("@" + var_i + "@", "" + val_enesimo);
			if (i < variables_ins.size() - 1) {
				expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, segmentEvaluado,
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

		expander.expandScriptExpressions(scriptsExpanded, variables_ins, groupVariableAndvalues_ins, item, 0);
		for (int i = 0; i < scriptsExpanded.size(); i++) {
			ExpandItem expandItem = scriptsExpanded.get(i);
			System.out.println(expandItem.getEvaluatedExpression());

		}
	}

}