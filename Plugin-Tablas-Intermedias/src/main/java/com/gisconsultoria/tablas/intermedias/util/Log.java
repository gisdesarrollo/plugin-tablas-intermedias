package com.gisconsultoria.tablas.intermedias.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;

public class Log {

	public void logTxt(String message) {
		Locale zonaHoraria = new Locale("es", "MX");
		Calendar fechaMex = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"), zonaHoraria);
		Date fechaHoy = fechaMex.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String fecha = sdf.format(fechaHoy);
		String nameTxt = "Bundle_" + fecha + ".txt";
		try {
			File logTxt = new File(nameTxt);
			if (!logTxt.exists()) {
				if (!logTxt.createNewFile()) {
					throw new Exception("Error no se pudo crear el archivo txt");
				}
			}
			FileWriter fw = new FileWriter(logTxt, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fecha + ": " + message);
			bw.append("\n");

			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
