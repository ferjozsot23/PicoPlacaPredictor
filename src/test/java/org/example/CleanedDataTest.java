package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CleanedDataTest {
    private CleanedData cleanedData;
    @BeforeEach
    public void setUp() {
        cleanedData = new CleanedData();
    }
    @org.junit.jupiter.api.Test
    void isValidPlate() {
        assertTrue(cleanedData.isValidPlate("PBU-1234"));
        assertTrue(cleanedData.isValidPlate("PBU-123"));

    }

    @org.junit.jupiter.api.Test
    void isValidPlateInvalid() {
        assertFalse(cleanedData.isValidPlate("Pbu/129L"));
        assertFalse(cleanedData.isValidDate(null));
        assertFalse(cleanedData.isValidDate(""));
    }

    @Test
    void isValidDate() {
        assertTrue(cleanedData.isValidDate("12-12-2024"));
        assertTrue(cleanedData.isValidDate("01-01-0001"));
        assertTrue(cleanedData.isValidDate("12-31-9999"));
    }

    @Test
    void isValidDateInvalid() {
        assertFalse(cleanedData.isValidDate(""));
        assertFalse(cleanedData.isValidDate(null));
        assertFalse(cleanedData.isValidDate("30-02-2000"));
        assertFalse(cleanedData.isValidDate("2000-30-02"));
    }

    @Test
    void isValidHour() {
        assertTrue(cleanedData.isValidHour("19:00"));
        assertTrue(cleanedData.isValidHour("9:00"));
    }

    @Test
    void isValidHourInvalid() {
        assertFalse(cleanedData.isValidHour("19H00"));
        assertFalse(cleanedData.isValidHour(null));
        assertFalse(cleanedData.isValidHour("Hola"));
    }

    @Test
    void getDay() throws ParseException {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
        Date sampleDate = DATE_FORMAT.parse("11-18-2023");
        int numberDay = cleanedData.getDay(sampleDate);

        assertEquals(6, numberDay);
    }

    @Test
    public void testFormatPlate() {
        String testPlate = "ABC-1234";
        int result = cleanedData.formatPlate(testPlate);

        assertEquals(4, result);
    }

    @Test
    void formatDate() throws ParseException {
        CleanedData cleanedData = new CleanedData();
        String testDate = "10-30-2023";
        int dayNumber = cleanedData.formatDate(testDate);

        assertEquals(1, dayNumber);
    }

    @Test
    void formatDateInvalid() throws ParseException {
        String testDate = "Hello";
        ParseException exception = null;
        try {
            cleanedData.formatDate(testDate);
        } catch (ParseException e){
            exception = e;
        }
        assertNotNull(exception);

    }
    @Test
    void formatHour() throws ParseException {
        String testHour = "15:30";

        Date result = cleanedData.formatHour(testHour);
        SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");
        String formattedResult = HOUR_FORMAT.format(result);

        assertEquals(testHour, formattedResult);
    }

    @Test
    public void testFormatHourInvalid() {
        String invalidHour = "70:70";
        ParseException exception = null;
        try {
            cleanedData.formatHour(invalidHour);
        } catch (ParseException e) {
            exception = e;
        }
        assertNotNull(exception);
    }
    @Test
    public void testClean() throws ParseException {

        String userPlate = "ABC-1234";
        String userDate = "11-18-2023";
        String userHour = "15:30";

        assertEquals(4, cleanedData.formatPlate(userPlate));
        assertEquals(6, cleanedData.formatDate(userDate));
        assertNotNull(cleanedData.formatHour(userHour));

        boolean result = cleanedData.clean(userPlate, userDate, userHour);
        assertTrue(result);
    }
}