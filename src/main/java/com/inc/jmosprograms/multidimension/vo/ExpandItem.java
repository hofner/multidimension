package com.inc.jmosprograms.multidimension.vo;

public class ExpandItem {
	private String variable;
	private String evaluatedExpression;
	private String resultset;
	private String fileNameResultsetCsv;
	private String fileNameImage;

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getResultset() {
		return resultset;
	}

	public void setResultset(String resultset) {
		this.resultset = resultset;
	}

	public String getEvaluatedExpression() {
		return evaluatedExpression;
	}

	public void setEvaluatedExpression(String evaluatedExpression) {
		this.evaluatedExpression = evaluatedExpression;
	}

	public String getFileNameImage() {
		return fileNameImage;
	}

	public void setFileNameImage(String fileNameImage) {
		this.fileNameImage = fileNameImage;
	}

	public String getFileNameResultset() {
		return fileNameResultsetCsv;
	}

	public void setFileNameResultset(String fileNameResultset) {
		this.fileNameResultsetCsv = fileNameResultset;
	}

}
