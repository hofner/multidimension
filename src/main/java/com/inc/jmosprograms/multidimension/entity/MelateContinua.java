package com.inc.jmosprograms.multidimension.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidimension")

public class MelateContinua {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "NPRODUCTO")
	private Integer producto;
	@Column
	private Integer concurso;
	@Column(name = "R_CONTINUA")
	private Integer rcontinua;
	@Column
	private Double bolsa;
	@Column
	private Date fecha;
	/// calculados
	@Column(name = "FECHA_STR")
	private String fechaStr;
	@Column(name = "NOMBRE_DIA")
	private String nombreDia;
	@Column(name = "NOMBRE_MES")
	private String nombreMes;
	@Column(name = "NUMERO_DIA_SEMANA")
	private int numeroDiaSemana;
	@Column(name = "NUMERO_DIA")
	private int numeroDia;
	@Column(name = "NUMERO_MES")
	private int numeroMes;
	@Column(name = "NUMERO_YEAR")
	private int numeroYear;
	@Column(name = "R1_IMPAR")
	private String r1Impar;
	@Column(name = "R2_IMPAR")
	private String r2Impar;
	@Column(name = "R3_IMPAR")
	private String r3Impar;
	@Column(name = "R4_IMPAR")
	private String r4Impar;
	@Column(name = "R5_IMPAR")
	private String r5Impar;
	@Column(name = "R6_IMPAR")
	private String r6Impar;
	@Column(name = "R7_IMPAR")
	private String r7Impar;
	@Column(name = "R1_PRIMO")
	private String r1Primo;
	@Column(name = "R2_PRIMO")
	private String r2Primo;
	@Column(name = "R3_PRIMO")
	private String r3Primo;
	@Column(name = "R4_PRIMO")
	private String r4Primo;
	@Column(name = "R5_PRIMO")
	private String r5Primo;
	@Column(name = "R6_PRIMO")
	private String r6Primo;
	@Column(name = "R7_PRIMO")
	private String r7Primo;
	@Column
	private Integer diff2;
	@Column
	private Integer diff3;
	@Column
	private Integer diff4;
	@Column
	private Integer diff5;
	@Column
	private Integer diff6;

	public MelateContinua() {

	}

	public MelateContinua(Melate melate) {
		// this.id=melate.;
		producto = melate.getProducto();
		concurso = melate.getConcurso();
		rcontinua = melate.getR1();
		bolsa = melate.getBolsa();
		fecha = melate.getFecha();
		fechaStr = melate.getFechaStr();
		nombreDia = melate.getNombreDia();
		nombreMes = melate.getNombreMes();
		numeroDiaSemana = melate.getNumeroDiaSemana();
		numeroDia = melate.getNumeroDia();
		numeroMes = melate.getNumeroMes();
		numeroYear = melate.getNumeroYear();
		r1Impar = melate.getR1Impar();
		r2Impar = melate.getR2Impar();
		r3Impar = melate.getR3Impar();
		r4Impar = melate.getR4Impar();
		r5Impar = melate.getR5Impar();
		r6Impar = melate.getR6Impar();
		r7Impar = melate.getR7Impar();
		r1Primo = melate.getR1Primo();
		r2Primo = melate.getR2Primo();
		r3Primo = melate.getR3Primo();
		r4Primo = melate.getR4Primo();
		r5Primo = melate.getR5Primo();
		r6Primo = melate.getR6Primo();
		r7Primo = melate.getR7Primo();
		diff2 = melate.getDiff2();
		diff3 = melate.getDiff3();
		diff4 = melate.getDiff4();
		diff5 = melate.getDiff5();
		diff6 = melate.getDiff6();

	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Double getBolsa() {
		return bolsa;
	}

	public void setBolsa(Double bolsa) {
		this.bolsa = bolsa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreDia() {
		return nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public String getNombreMes() {
		return nombreMes;
	}

	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}

	public int getNumeroDia() {
		return numeroDia;
	}

	public void setNumeroDia(int numeroDia) {
		this.numeroDia = numeroDia;
	}

	public int getNumeroMes() {
		return numeroMes;
	}

	public void setNumeroMes(int numeroMes) {
		this.numeroMes = numeroMes;
	}

	public Integer getDiff2() {
		return diff2;
	}

	public void setDiff2(Integer diff2) {
		this.diff2 = diff2;
	}

	public Integer getDiff3() {
		return diff3;
	}

	public void setDiff3(Integer diff3) {
		this.diff3 = diff3;
	}

	public Integer getDiff4() {
		return diff4;
	}

	public void setDiff4(Integer diff4) {
		this.diff4 = diff4;
	}

	public Integer getDiff5() {
		return diff5;
	}

	public void setDiff5(Integer diff5) {
		this.diff5 = diff5;
	}

	public Integer getDiff6() {
		return diff6;
	}

	public void setDiff6(Integer diff6) {
		this.diff6 = diff6;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public int getNumeroYear() {
		return numeroYear;
	}

	public void setNumeroYear(int numeroYear) {
		this.numeroYear = numeroYear;
	}

	public int getNumeroDiaSemana() {
		return numeroDiaSemana;
	}

	public void setNumeroDiaSemana(int numeroDiaSemana) {
		this.numeroDiaSemana = numeroDiaSemana;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getR1Impar() {
		return r1Impar;
	}

	public void setR1Impar(String r1Impar) {
		this.r1Impar = r1Impar;
	}

	public String getR2Impar() {
		return r2Impar;
	}

	public void setR2Impar(String r2Impar) {
		this.r2Impar = r2Impar;
	}

	public String getR3Impar() {
		return r3Impar;
	}

	public void setR3Impar(String r3Impar) {
		this.r3Impar = r3Impar;
	}

	public String getR4Impar() {
		return r4Impar;
	}

	public void setR4Impar(String r4Impar) {
		this.r4Impar = r4Impar;
	}

	public String getR5Impar() {
		return r5Impar;
	}

	public void setR5Impar(String r5Impar) {
		this.r5Impar = r5Impar;
	}

	public String getR6Impar() {
		return r6Impar;
	}

	public void setR6Impar(String r6Impar) {
		this.r6Impar = r6Impar;
	}

	public String getR7Impar() {
		return r7Impar;
	}

	public void setR7Impar(String r7Impar) {
		this.r7Impar = r7Impar;
	}

	public String getR1Primo() {
		return r1Primo;
	}

	public void setR1Primo(String r1Primo) {
		this.r1Primo = r1Primo;
	}

	public String getR2Primo() {
		return r2Primo;
	}

	public void setR2Primo(String r2Primo) {
		this.r2Primo = r2Primo;
	}

	public String getR3Primo() {
		return r3Primo;
	}

	public void setR3Primo(String r3Primo) {
		this.r3Primo = r3Primo;
	}

	public String getR4Primo() {
		return r4Primo;
	}

	public void setR4Primo(String r4Primo) {
		this.r4Primo = r4Primo;
	}

	public String getR5Primo() {
		return r5Primo;
	}

	public void setR5Primo(String r5Primo) {
		this.r5Primo = r5Primo;
	}

	public String getR6Primo() {
		return r6Primo;
	}

	public void setR6Primo(String r6Primo) {
		this.r6Primo = r6Primo;
	}

	public String getR7Primo() {
		return r7Primo;
	}

	public void setR7Primo(String r7Primo) {
		this.r7Primo = r7Primo;
	}

	public String getFechaStr() {
		return fechaStr;
	}

	public void setFechaStr(String fechaStr) {
		this.fechaStr = fechaStr;
	}

	public Integer getRcontinua() {
		return rcontinua;
	}

	public void setRcontinua(Integer rcontinua) {
		this.rcontinua = rcontinua;
	}

}
