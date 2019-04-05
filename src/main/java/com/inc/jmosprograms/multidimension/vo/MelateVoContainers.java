package com.inc.jmosprograms.multidimension.vo;

import java.util.ArrayList;

import com.inc.jmosprograms.multidimension.entity.Melate;
import com.inc.jmosprograms.multidimension.entity.MelateContinua;

public class MelateVoContainers {
	private ArrayList<Melate> result;
	private ArrayList<MelateContinua> resultContinua;

	public ArrayList<Melate> getResult() {
		return result;
	}

	public void setResult(ArrayList<Melate> result) {
		this.result = result;
	}

	public ArrayList<MelateContinua> getResultContinua() {
		return resultContinua;
	}

	public void setResultContinua(ArrayList<MelateContinua> resultContinua) {
		this.resultContinua = resultContinua;
	}

}
