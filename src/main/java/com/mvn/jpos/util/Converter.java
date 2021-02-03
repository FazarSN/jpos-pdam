package com.mvn.jpos.util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.json.simple.JSONObject;

public class Converter {

    private Converter() {
    }

    private static ISOMsg loadPackager() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(new GenericPackager("cfg/pdamPackager.xml"));
        return isoMsg;
    }

    public static String fromISOtoJSON(String isoMessage) throws ISOException {
        byte[] isoMsgByte = new byte[isoMessage.length()];
        for (int i = 0; i < isoMsgByte.length; i++) {
            isoMsgByte[i] = (byte) (int) isoMessage.charAt(i);
        }

        ISOMsg packageMsg = loadPackager();
        packageMsg.unpack(isoMsgByte);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MTI", packageMsg.getMTI());
        for (int i = 1; i <= packageMsg.getMaxField(); i++) {
            if (packageMsg.hasField(i))
                jsonObject.put(i, packageMsg.getString(i));
        }
        return jsonObject.toJSONString();
    }

    public static String fromJSONtoISO(String mti) throws ISOException {
        ISOMsg packageMsg = loadPackager();

        packageMsg.set(0, mti);
        switch (mti) {
            case "0100":
                requestPelanggan(packageMsg);
                break;
            case "0800":
                requestNetwork(packageMsg);
                break;
            default:
                break;
        }

        byte[] isoByteMsg = packageMsg.pack();

        String isoMessage = "";
        for (int i = 0; i < isoByteMsg.length; i++) {
            isoMessage += (char) isoByteMsg[i];
        }
        return isoMessage;
    }

    private static ISOMsg requestPelanggan(ISOMsg packageMsg) throws ISOException {
        // TODO : hard coded
        packageMsg.set(7, "0106105341");
        packageMsg.set(11, "000003");
        packageMsg.set(33, "0101210061");
        packageMsg.set(39, "001");
        packageMsg.set(41, "20000000");
        packageMsg.set(65, "0");

        return packageMsg;
    }

    private static ISOMsg requestNetwork(ISOMsg packageMsg) throws ISOException {
        // TODO : hard coded
        packageMsg.set(7, "0203115341");
        packageMsg.set(11, "000003");
        packageMsg.set(41, "20000000");
        packageMsg.set(65, "");

        return packageMsg;
    }

}
