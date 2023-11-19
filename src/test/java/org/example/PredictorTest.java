package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PredictorTest {
    Predictor predictor;
    SimpleDateFormat HOUR_FORMAT;
    @BeforeEach
    public void setUp() {
        predictor = new Predictor(new View());
        HOUR_FORMAT = new SimpleDateFormat("HH:mm");
    }

    @Test
    void isRestrictedDay_Restricted() {
        int digitPlate = 6;
        int day = 3;
        assertTrue(predictor.isRestrictedDay(digitPlate, day));
    }

    @Test
    void isRestrictedDay_NoRestricted() {
        int digitPlate = 6;
        int day = 4;
        assertFalse(predictor.isRestrictedDay(digitPlate, day));
    }

    @Test
    void isRestrictedHour_Restricted() throws ParseException {

        Date hour_lim_inf1 = HOUR_FORMAT.parse("6:00");
        Date hour_lim_sup1 = HOUR_FORMAT.parse("9:30");
        Date hour_lim_inf2 = HOUR_FORMAT.parse("16:00");
        Date hour_lim_sup2 = HOUR_FORMAT.parse("20:00");

        assertTrue(predictor.isRestrictedHour(hour_lim_inf1));
        assertTrue(predictor.isRestrictedHour(hour_lim_sup1));
        assertTrue(predictor.isRestrictedHour(hour_lim_inf2));
        assertTrue(predictor.isRestrictedHour(hour_lim_sup2));
    }
    @Test
    void isRestrictedHour_NoRestricted() throws ParseException {


        Date hour_lim_inf1 = HOUR_FORMAT.parse("05:59");
        Date hour_lim_sup1 = HOUR_FORMAT.parse("9:31");
        Date hour_lim_inf2 = HOUR_FORMAT.parse("15:59");
        Date hour_lim_sup2 = HOUR_FORMAT.parse("20:01");

        assertFalse(predictor.isRestrictedHour(hour_lim_inf1));
        assertFalse(predictor.isRestrictedHour(hour_lim_sup1));
        assertFalse(predictor.isRestrictedHour(hour_lim_inf2));
        assertFalse(predictor.isRestrictedHour(hour_lim_sup2));
    }
   

}