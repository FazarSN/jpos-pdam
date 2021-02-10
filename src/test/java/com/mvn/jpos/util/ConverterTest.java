package com.mvn.jpos.util;

import org.jpos.iso.ISOException;
import org.junit.Test;

public class ConverterTest {

	@Test
	public void fromISOtoJSONTest() {
		String message = "";
		try {
			message = Converter
					.fromISOtoJSON("0100822000008280000080000000000000000201114011000058010101000200120000008");
		} catch (ISOException e) {
			e.printStackTrace();
		}
		System.out.println(message);
	}

	@Test
	public void fromJSONtoISOTest() {
		String message = "";
		String header = "";
		try {
			message = Converter.fromJSONtoISO("0800");

			header = String.format("%04d", message.length());
		} catch (ISOException e) {
			e.printStackTrace();
		}
		System.out.println(header + message);
	}

}
