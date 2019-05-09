package com.inc.jmosprograms.multidimension.sequencer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inc.jmosprograms.multidimension.config.ApplicationProperties;

@Component
public class Finder {
	@Autowired
	ApplicationProperties props;

	public String findFileToProcess() {

		String ruta = props.getSourcePath();
		File directory = new File(ruta);
		File[] listFiles = directory.listFiles();
		String completeFolderFile = null;
		if (listFiles != null) {
			completeFolderFile = ruta + "\\" + listFiles[0].getName();
		}
		return completeFolderFile;

	}

	public void moveFoundFile() {

		String sourcePath = props.getSourcePath();
		String carpetaDestino = props.getRutaProcesado();
		File directory = new File(sourcePath);
		String[] archivos = directory.list();
		if (archivos != null) {
			String file = archivos[0];
			try {
				FileUtils.moveFile(FileUtils.getFile(sourcePath + "\\" + file),
						FileUtils.getFile(carpetaDestino + "\\" + file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
