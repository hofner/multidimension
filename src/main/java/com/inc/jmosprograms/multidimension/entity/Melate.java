package com.inc.jmosprograms.multidimension.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "multidimension")

public class Melate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "NPRODUCTO")
	private Integer producto;
	@Column
	private Integer concurso;
	@Column
	private Integer r1;
	@Column
	private Integer r2;
	@Column
	private Integer r3;
	@Column
	private Integer r4;
	@Column
	private Integer r5;
	@Column
	private Integer r6;
	@Column
	private Integer r7;
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

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Integer getR1() {
		return r1;
	}

	public void setR1(Integer r1) {
		this.r1 = r1;
	}

	public Integer getR2() {
		return r2;
	}

	public void setR2(Integer r2) {
		this.r2 = r2;
	}

	public Integer getR3() {
		return r3;
	}

	public void setR3(Integer r3) {
		this.r3 = r3;
	}

	public Integer getR4() {
		return r4;
	}

	public void setR4(Integer r4) {
		this.r4 = r4;
	}

	public Integer getR5() {
		return r5;
	}

	public void setR5(Integer r5) {
		this.r5 = r5;
	}

	public Integer getR6() {
		return r6;
	}

	public void setR6(Integer r6) {
		this.r6 = r6;
	}

	public Integer getR7() {
		return r7;
	}

	public void setR7(Integer r7) {
		this.r7 = r7;
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

}
