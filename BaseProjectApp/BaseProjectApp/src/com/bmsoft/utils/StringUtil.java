package com.bmsoft.utils;

import java.math.BigDecimal;

public class StringUtil {

	public static boolean isEmptyOrNull(String string) {
		if (string == null)
			return true;

		return string.equalsIgnoreCase("");
	}

	public static boolean isEmpty(String string) {
		return string.equalsIgnoreCase("");
	}

	public static boolean isNumber(String in) {

		try {

			int valor = Integer.parseInt(in);

		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static String removeExtraSpace(String valor) {
		String regex = "\\s{2,}";
		return valor.replaceAll(regex, " ");
	}

	public static boolean stringCompare(String string1, String string2) {
		if (string1 == null || string2 == null)
			return false;

		return string1.equals(string2);
	}

	public static String getExtensionArchive(String file) {
		if (file.indexOf(".") < 0)
			return "";

		return file.substring(file.indexOf(".") + 1, file.length());
	}

	public static String formatearNumero(BigDecimal numero) {
		if (numero == null)
			return "";
		try {
			StringBuilder numeroStr = new StringBuilder(numero.toString().replace(".", ","));
			int finalParteEntera = numeroStr.toString().indexOf(",") > -1 ? numeroStr.toString().indexOf(",")
					: numeroStr.length();
			int contdigitos = 0;
			for (int i = finalParteEntera; i > 0; i--) {
				contdigitos++;
				if (contdigitos == 3 && i != 1) {
					numeroStr.insert(i - 1, ".");
					contdigitos = 0;
				}
			}

			if (numeroStr.toString().indexOf(",") > -1
					&& (numeroStr.length() - numeroStr.toString().indexOf(",")) > 2) {
				return numeroStr.toString().substring(0, numeroStr.toString().indexOf(",") + 3);
			} else {
				return numeroStr.toString();
			}

		} catch (Exception e) {

		}

		return "";
	}
}
