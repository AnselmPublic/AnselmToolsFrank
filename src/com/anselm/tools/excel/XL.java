package com.anselm.tools.excel;

public class XL {
	public static String getFileTypeExtension(String file) {
		return file.substring(file.indexOf('.') + 1, file.length());
	}
}
