package com.example.mediatek86formations.outils;

import junit.framework.TestCase;
import java.util.Date;

public class MesOutilsTest extends TestCase {

    // création d'une date au format String
    private String firstdate = "2020-02-01 00:00:00";
    // conversion de la date au format String en format Date
    private Date seconddate = MesOutils.convertStringToDate(firstdate, "yyyy-MM-dd HH:mm:ss");

    public void testConvertStringToDate() {
        assertEquals(seconddate, MesOutils.convertStringToDate(firstdate, "yyyy-MM-dd HH:mm:ss"));
    }

    public void testConvertDateToString() {
        assertEquals(firstdate, MesOutils.convertDateToString(seconddate));
    }
}