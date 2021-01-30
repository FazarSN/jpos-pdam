package com.mvn.jpos.util;

import org.jpos.iso.ISOException;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void fromISOtoJSONTest() {
        String message = "";
        try {
            message = Converter
                    .fromISOtoJSON("01008220000082800000000000000000000001061053410000030101210061001200000PD");
        } catch (ISOException e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }

    @Test
    public void fromJSONtoISOTest() {
        String message = "";
        try {
            message = Converter.fromJSONtoISO("0100");
        } catch (ISOException e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }

}
