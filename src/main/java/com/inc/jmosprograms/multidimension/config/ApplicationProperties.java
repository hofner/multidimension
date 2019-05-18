package com.inc.jmosprograms.multidimension.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	@Value("${interpolation.file.sourcepath}")
	private String sourcePath;
	@Value("${interpolation.file.rutaprocesado}")
	private String rutaProcesado;

	@Value("${interpolation.file.sqlcsvhistograma}")
	private String sqlCsvHistograma;
	@Value("${interpolation.file.sqlcsvplot}")
	private String sqlCsvPlot;
	@Value("${interpolation.file.sqlcsvcontinua}")
	private String sqlCsvContinua;
	@Value("${interpolation.file.projectroot}")
	private String projectRoot;
	@Value("${interpolation.file.rconsolehistogram}")
	private String rConsoleHistogram;
	@Value("${interpolation.file.rconsoleplot}")
	private String rConsolePlot;
	@Value("${interpolation.file.rconsolecontinua}")
	private String rConsoleContinua;

	@Value("${spring.datasource.jdbcUrl}")
	private String jdbcUrl;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${interpolation.url.multdimensionurl}")
	private String urlReadMultidimension;

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

	public String getSqlCsvHistograma() {
		return sqlCsvHistograma;
	}

	public void setSqlCsvHistograma(String sqlCsvHistograma) {
		this.sqlCsvHistograma = sqlCsvHistograma;
	}

	public String getSqlCsvPlot() {
		return sqlCsvPlot;
	}

	public void setSqlCsvPlot(String sqlCsvPlot) {
		this.sqlCsvPlot = sqlCsvPlot;
	}

	public String getProjectRoot() {
		return projectRoot;
	}

	public void setProjectRoot(String projectRoot) {
		this.projectRoot = projectRoot;
	}

	public String getrConsoleHistogram() {
		return rConsoleHistogram;
	}

	public void setrConsoleHistogram(String rConsoleHistogram) {
		this.rConsoleHistogram = rConsoleHistogram;
	}

	public String getrConsolePlot() {
		return rConsolePlot;
	}

	public void setrConsolePlot(String rConsolePlot) {
		this.rConsolePlot = rConsolePlot;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlReadMultidimension() {
		return urlReadMultidimension;
	}

	public void setUrlReadMultidimension(String urlReadMultidimension) {
		this.urlReadMultidimension = urlReadMultidimension;
	}

	public String getSqlCsvContinua() {
		return sqlCsvContinua;
	}

	public void setSqlCsvContinua(String sqlCsvContinua) {
		this.sqlCsvContinua = sqlCsvContinua;
	}

	public String getrConsoleContinua() {
		return rConsoleContinua;
	}

	public void setrConsoleContinua(String rConsoleContinua) {
		this.rConsoleContinua = rConsoleContinua;
	}

}
