package com.mvn.jpos.util;

import org.jpos.iso.ISOException;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void fromISOtoJSONTest() {
        String message = "";
        try {
            message = Converter
                    .fromISOtoJSON("010002200000828000000106105341000003010121006100102000000");
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
