package com.inc.jmosprograms.multidimension.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.inc.jmosprograms.multidimension.entity.Melate;
import com.inc.jmosprograms.multidimension.entity.MelateContinua;
import com.inc.jmosprograms.multidimension.repository.MelateContinuaRepository;
import com.inc.jmosprograms.multidimension.repository.MelateRepository;
import com.inc.jmosprograms.multidimension.vo.MELATE_COLUMNS_ENUM;
import com.inc.jmosprograms.multidimension.vo.MelateVoContainers;

@Service
public class ReaderService {
	Object[] readerResults;
	@Autowired
	MelateRepository melateRepository;
	@Autowired
	MelateContinuaRepository melateContinuaRepository;
	private static Logger LOG = Logger.getLogger(ReaderService.class);
	// It might be replaced by this another rule : Scheduled(cron = "0 6 16 * *
	// ?")
	// Now it's set to every 5 hours
	private static final int TIME_INTERVAL = 6 * 60 * 60 * 1000;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(fixedRate = TIME_INTERVAL)
	public void loadAllResults() {
		LOG.info("Corriendolo :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));
		MelateVoContainers melatesContainers = loadFile("C:\\jmNewDevelopment\\development-R\\Melate20190704.csv");
		melateRepository.saveAll(melatesContainers.getResult());
		melateContinuaRepository.saveAll(melatesContainers.getResultContinua());
		LOG.info("TERMINADO :: " + melatesContainers.getResult().size() + " INSERTED MELATE ROWS- "
				+ dateTimeFormatter.format(LocalDateTime.now()));
		LOG.info("TERMINADO :: " + melatesContainers.getResultContinua().size() + " INSERTED MELATESCONTINUAS ROWS- "
				+ dateTimeFormatter.format(LocalDateTime.now()));

	}

	MelateVoContainers loadFile(String filePath) {
		readerResults = new Object[2];
		ArrayList<Melate> arryResults = null;
		ArrayList<MelateContinua> arrysContinuas = null;
		try {

			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String ln = "";
			int i = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Locale localeMX = new Locale("es", "MX");
			SimpleDateFormat nombreDiaFormat = new SimpleDateFormat("EEEE", localeMX);
			SimpleDateFormat nombreMesFormat = new SimpleDateFormat("MMMM", localeMX);

			SimpleDateFormat numeroDiaSemanaFormat = new SimpleDateFormat("u");
			SimpleDateFormat sacarNumeroDiaFormat = new SimpleDateFormat("dd", localeMX);
			SimpleDateFormat sacarNumeroMesFormat = new SimpleDateFormat("MM", localeMX);
			SimpleDateFormat sacarNumeroYearFormat = new SimpleDateFormat("yyyy", localeMX);
			arryResults = new ArrayList<>();
			arrysContinuas = new ArrayList<>();
			while (ln != null) {
				ln = br.readLine();
				Melate mela = new Melate();
				if (i != 0 && ln != null && ln.length() > 10) {
					int j = 0;
					StringTokenizer st = new StringTokenizer(ln, ",");
					while (st.hasMoreTokens()) {
						String token = st.nextToken();
						switch (j) {
						case MELATE_COLUMNS_ENUM.NPRODUCTO:
							mela.setProducto(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.CONCURSO:
							mela.setConcurso(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R1:
							mela.setR1(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R2:
							mela.setR2(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R3:
							mela.setR3(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R4:
							mela.setR4(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R5:
							mela.setR5(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R6:
							mela.setR6(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.R7:
							mela.setR7(Integer.parseInt(token));
							break;
						case MELATE_COLUMNS_ENUM.BOLSA:
							mela.setBolsa(Double.parseDouble(token));
							break;
						case MELATE_COLUMNS_ENUM.FECHA:
							try {
								Date date = sdf.parse(token);
								mela.setFecha(date);
								mela.setFechaStr(sdf.format(date));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							break;

						default:
							break;
						}
						j++;
					}
					/// Ahora llenamos los calculados
					String originalDia = nombreDiaFormat.format(mela.getFecha()).toUpperCase();
					if (originalDia.startsWith("M")) {
						mela.setNombreDia("MIERCOLES");
					} else {
						mela.setNombreDia(originalDia);
					}
					mela.setNumeroDiaSemana(Integer.parseInt(numeroDiaSemanaFormat.format(mela.getFecha())));
					mela.setNombreMes(nombreMesFormat.format(mela.getFecha()).toUpperCase());
					mela.setNumeroDia(Integer.parseInt(sacarNumeroDiaFormat.format(mela.getFecha())));
					mela.setNumeroMes(Integer.parseInt(sacarNumeroMesFormat.format(mela.getFecha())));
					mela.setNumeroYear(Integer.parseInt(sacarNumeroYearFormat.format(mela.getFecha())));

					mela.setR1Impar((mela.getR1() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR2Impar((mela.getR2() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR3Impar((mela.getR3() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR4Impar((mela.getR4() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR5Impar((mela.getR5() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR6Impar((mela.getR6() % 2) == 1 ? "IMPAR" : "PAR");
					mela.setR7Impar((mela.getR7() % 2) == 1 ? "IMPAR" : "PAR");

					mela.setR1Primo(isPrime(mela.getR1()) ? "PRIMO" : "NO_PRIMO");
					mela.setR2Primo(isPrime(mela.getR2()) ? "PRIMO" : "NO_PRIMO");
					mela.setR3Primo(isPrime(mela.getR3()) ? "PRIMO" : "NO_PRIMO");
					mela.setR4Primo(isPrime(mela.getR4()) ? "PRIMO" : "NO_PRIMO");
					mela.setR5Primo(isPrime(mela.getR5()) ? "PRIMO" : "NO_PRIMO");
					mela.setR6Primo(isPrime(mela.getR6()) ? "PRIMO" : "NO_PRIMO");
					mela.setR7Primo(isPrime(mela.getR7()) ? "PRIMO" : "NO_PRIMO");

					mela.setDiff2(mela.getR2() - mela.getR1());
					mela.setDiff3(mela.getR3() - mela.getR2());
					mela.setDiff4(mela.getR4() - mela.getR3());
					mela.setDiff5(mela.getR5() - mela.getR4());
					mela.setDiff6(mela.getR6() - mela.getR5());
					arryResults.add(mela);
					// aqui vamos a crear el entity donde las R's son continuas
					// o discretas como le puse

					MelateContinua continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR1());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR2());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR3());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR4());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR5());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR6());
					arrysContinuas.add(continua);

					continua = new MelateContinua(mela);
					continua.setRcontinua(mela.getR7());
					arrysContinuas.add(continua);

				}
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MelateVoContainers melateVoContainers = new MelateVoContainers();
		melateVoContainers.setResult(arryResults);
		melateVoContainers.setResultContinua(arrysContinuas);
		;
		return melateVoContainers;
	}

	/**
	 * @param n
	 * @return
	 */
	public boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
