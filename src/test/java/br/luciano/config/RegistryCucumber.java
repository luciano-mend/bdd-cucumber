package br.luciano.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.cucumber.java.ParameterType;

public class RegistryCucumber {

	@ParameterType(".*")
	public Date data(String s) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return format.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
