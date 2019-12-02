package com.lucianoortizsilva.workshoppedido.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	
	
	
	
	public static List<Integer> decodeIntList(String texto) {
		String[] array = texto.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			list.add(Integer.parseInt(array[i]));
		}
		return list;
	}
	
	
	
	
	
	public static String decodeParam(String texto) {
		try {
			return URLDecoder.decode(texto, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
