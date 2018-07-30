package br.com.ufba.votacao.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import br.com.ufba.votacao.utils.IConstantes;

public class Enquetes implements Comparable<Enquetes> {
	
	private String id, titulo; 
	public String numOpcoes, op1, qtdOp1, op2, qtdOp2, op3, qtdOp3, op4, qtdOp4, op5, qtdOp5, dtf;
	
	public DateTimeFormatter formatter = DateTimeFormatter.ofPattern(IConstantes.DATEFORMAT);
	public LocalDate dateTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Enquetes(){
		id = "";
		titulo = numOpcoes = op1 = qtdOp1 = op2 = qtdOp2 = op3 = qtdOp3 = op4 = qtdOp4 = op5 = qtdOp5 = "";
		qtdOp1 = qtdOp2 = qtdOp3 = qtdOp4 = qtdOp5 = "0";
		dtf = "01/01/0001";
		dateTime = LocalDate.parse(this.dtf, formatter);
	}
	
	LocalDate toLocDat() {
		return this.dateTime = LocalDate.parse(this.dtf, formatter);
	}
	
	@Override
	public int compareTo(Enquetes o) {
	  return (toLocDat().compareTo(o.toLocDat()));
	}
	
}
