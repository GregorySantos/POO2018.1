package br.com.ufba.votacao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**Classe utilitária para validar entradas do tipo data
 * no formato especificado.
* @author Gregory Santos
* @author Yndyra Pinheiro
* @author Mauro Meneses
* @author Yuri Oliveira
* @author Guilherme Costa
* @author Lucas
* @version 1.00
* @since Release 01 da aplicação
*/

public class DateValidator {
	
	private boolean isNumber(char x) {
		return ('0' <= x && x <= '9');
	}

	public boolean isThisDateValid(String dateToValidate, String dateFormat){

		if(dateToValidate == null || dateToValidate.length() != 10){
			return false;
		}
		
		if(!isNumber(dateToValidate.charAt(0)) || !isNumber(dateToValidate.charAt(1)) 
		|| !isNumber(dateToValidate.charAt(3)) || !isNumber(dateToValidate.charAt(4)) 
		|| !isNumber(dateToValidate.charAt(6)) || !isNumber(dateToValidate.charAt(7)) 
		|| !isNumber(dateToValidate.charAt(8)) || !isNumber(dateToValidate.charAt(9)))
			return false;
		
		if(dateToValidate.charAt(2) != '/' || dateToValidate.charAt(5) != '/')
			return false;

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {

			Date date = sdf.parse(dateToValidate);

		} catch (ParseException e) {

			//e.printStackTrace();
			return false;
		}

		return true;
	}

}