package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class RecordBookTest {

    @Test
    public void recordBookTest() {
        RecordBook recordBook = new RecordBook("Grigory", "Chernikov", false);
        recordBook.addGrades(1, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(1, "assignment", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(1, "colloquium", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.GOOD))));
        recordBook.addGrades(1, "test", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(2, "assignment", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.GOOD, Mark.EXCELLENT))));
        recordBook.addGrades(2, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(3, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.GOOD))));
        recordBook.addGrades(4, "exam", new ArrayList<>((Arrays.asList(
                Mark.GOOD, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.setQualificationWork(5);
        assertEquals(4.833333333333333, recordBook.calculateAverage());
        assertFalse(recordBook.isItBudget());
        assertEquals(true, recordBook.isHonorDegree());
        assertFalse(recordBook.higherSchoolarship());
        assertEquals("Grigory Chernikov", recordBook.getFullName());

        RecordBook book = new RecordBook("Ildar", "Fitkulin", true);
        book.addGrades(1, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.addGrades(1, "assignment", new ArrayList<>((Arrays.asList(
                Mark.GOOD, Mark.GOOD, Mark.GOOD))));
        book.addGrades(1, "colloquium", new ArrayList<>((Arrays.asList(
                Mark.SATISFACTORY, Mark.EXCELLENT, Mark.GOOD))));
        book.addGrades(1, "test", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.addGrades(2, "assignment", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.UNSATISFACTORY, Mark.EXCELLENT))));
        book.addGrades(2, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.UNSATISFACTORY, Mark.EXCELLENT))));
        book.addGrades(3, "exam", new ArrayList<>((Arrays.asList(
                Mark.EXCELLENT, Mark.EXCELLENT, Mark.SATISFACTORY))));
        book.addGrades(4, "exam", new ArrayList<>((Arrays.asList(
                Mark.GOOD, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.setQualificationWork(4);
        assertEquals(4.375, book.calculateAverage());
        assertEquals(false, book.isItBudget());
        assertEquals(false, book.isHonorDegree());
        assertEquals(false, book.higherSchoolarship());
        assertEquals("Ildar Fitkulin", book.getFullName());
    }
}