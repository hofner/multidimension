package com.inc.jmosprograms.multidimension.vo;

import java.util.ArrayList;
import java.util.TreeMap;

import com.inc.jmosprograms.multidimension.entity.Melate;
import com.inc.jmosprograms.multidimension.entity.MelateContinua;

public class MelateVoContainers {
	private ArrayList<Melate> result;
	private TreeMap<Integer, ArrayList<MelateContinua>> resultContinua;

	public ArrayList<Melate> getResult() {
		return result;
	}

	public void setResult(ArrayList<Melate> result) {
		this.result = result;
	}

	public TreeMap<Integer, ArrayList<MelateContinua>> getResultContinua() {
		return resultContinua;
	}

	public void setResultContinua(TreeMap<Integer, ArrayList<MelateContinua>> resultContinua) {
		this.resultContinua = resultContinua;
	}
}
