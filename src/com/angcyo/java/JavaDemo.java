package com.angcyo.java;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by angcyo on 2016-02-28 22:03.
 */
public class JavaDemo {
    public static PrintStream p = System.out;

    public static void main(String... args) {
        String s = "0000010000000258000000D5000000060800031CFF02FF02FF02FF02FF02FF02FF02C1CAA6B40660FF024E4587221EFC06E34BFF022BC2B777792EDCED0FF8ED08488D80FF022931EA98E07C3F62EC291AE92F8FA7646CC3EC3434AD472F5D7FFF02BC93655E226AC749E8B30A264F5D8C54E38880FF021943B83C4314727E0B20A34903548EA0FF02A153A0FCCC6DC61E5BF5A549D9E0FF02A3B07216DD6FF591F4D914AEFF02C2D793D58CD1EB83B87AA8FF0278F6AA1266BF5BD18A108206DA70ACEEE32280980512CCF0FD38FF0239DD0C8533C54A5CD8569EBBD8ABBE033BB30ADCBB8B2B9036213AD2DF9E1651726505E50A895CEBCF706BA3632E857614CA28FE2A56CD0C583360FF02C98F08B292C5E951521107E108A26B63D16FFA9CBC33AD987A91DFD44BD7AB02013FF8929BD03DD58D84F9C5A18B75BEF6BCEC4B4C90C6D92B0CC3484BC5FF028DB165DE1CCC1F4F881000005D4C17345616C976CA893B4A33B1051C9CC239F8FF0250A7B2B60696CA1D40B0626124CCB399CC073B0F0EFF0285724BA8F6FF000EB1A11CA2BB9EE681053C22128C7BF24CBC92FF02613F5052318010FC23D7DDF1050C51DFFF023ABC3ABBFA32613097FB39CE2F653B966C84FF022198CF9C669C34AE196EEAE7D1F61B951B06FAD667586DE8678069ACA0FF0298CF83861E773E2880B9359E330309B347649F30EA66AD45FF02326F8E15C2DAA52F45E86DAFABF135CA16A0BA8273CC2760FF02363EE38CF57F1F24D3594D10FF02EFD2382E3824FF0245413072DB41C0FF02B3EDADEDD9F40CFF02962CC0FF02FF02FF02FF02FF02";

        try {
            byte[] bytes = s.getBytes("utf8");
            p.println(new String(bytes));
            p.println(bytes[1]);
            p.println(((char) bytes[5]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
