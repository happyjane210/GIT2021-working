package com.git.controller.lib;

public class TesxProcesser {

	public static boolean isEmptyText(String text) {
		return text == null || text.isEmpty();
	}

	public static String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
}
