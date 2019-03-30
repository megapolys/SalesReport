package com.db

class AppTest extends GroovyTestCase {

    void testFirstValidate() {
        String line = "   ";
        String line1 = "";
        assertEquals(App.executeMethod(line), "ERROR");
        assertEquals(App.executeMethod(line1), "ERROR");

        String line2 = "NEWPRODUCT iphone 1";
        String line3 = "NEWPRODUCT iphone";
        String line4 = "PURCHASE iphone 3 1000 01.01.2017";
        String line5 = "DEMAND iphone 01.03.2017";
        String line6 = "SALESREPORT iphone 02.03.2017 1234";
        assertEquals(App.executeMethod(line2), "ERROR");
        assertEquals(App.executeMethod(line3), "OK");
        assertEquals(App.executeMethod(line4), "OK");
        assertEquals(App.executeMethod(line5), "ERROR");
        assertEquals(App.executeMethod(line6), "ERROR");
        DB.clear();
    }

    void testSecondValidate() {
        String line1 = "PURCHASE iphone 3 1000.0.0 01.01.2017";
        String line2 = "DEMAND iphone 2.0a 3000.0 01.03.2017";
        String line3 = "SALESREPORT iphone 02.37.2017";

        assertEquals(App.executeMethod(line1), "ERROR")
        assertEquals(App.executeMethod(line2), "ERROR")
        assertEquals(App.executeMethod(line3), "ERROR")
        DB.clear();
    }

    void testExecuteMethod() {
        String line = "NEWPRODUCT iphone";
        String line1 = "NEWPRODUCT iphone";
        String line2 = "PURCHASE iphone 1 1000 01.01.2017";
        String line3 = "PURCHASE iphone 2 2000 01.02.2017";
        String line4 = "DEMAND iphone 2 5000 01.03.2017";
        String line5 = "SALESREPORT iphone 02.03.2017";

        assertEquals(App.executeMethod(line), "OK")
        assertEquals(App.executeMethod(line1), "ERROR")
        assertEquals(App.executeMethod(line2), "OK")
        assertEquals(App.executeMethod(line3), "OK")
        assertEquals(App.executeMethod(line4), "OK")
        assertEquals(App.executeMethod(line5), "7000.0")
        DB.clear();

    }

}
