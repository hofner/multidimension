package com.inc.jmosprograms.multidimension.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	@Value("${interpolation.file.sourcepath}")
	private String sourcePath;
	@Value("${interpolation.file.rutaprocesado}")
	private String rutaProcesado;

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getRutaProcesado() {
		return rutaProcesado;
	}

	public void setRutaProcesado(String rutaProcesado) {
		this.rutaProcesado = rutaProcesado;
	}

}
