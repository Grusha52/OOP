package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class RecordBookTest {

    @Test
    public void recordBookTest() {

        RecordBook recordBook = new RecordBook("Grigory", "Chernikov", false);
        recordBook.addGrades(1, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(1, "assignment", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(1, "colloquium", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.GOOD))));
        recordBook.addGrades(1, "test", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(2, "assignment", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.GOOD, Mark.EXCELLENT))));
        recordBook.addGrades(2, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.addGrades(3, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.GOOD))));
        recordBook.addGrades(4, "exam", new ArrayList<>((Arrays.asList(Mark.GOOD, Mark.EXCELLENT, Mark.EXCELLENT))));
        recordBook.setQualificationWork(5);
        System.out.println(recordBook.calculateAverage());
        System.out.println(recordBook.isItBudget());
        System.out.println(recordBook.isHonorDegree());
        System.out.println(recordBook.higherSchoolarship());
        System.out.println(recordBook.getFullName());

        RecordBook book = new RecordBook("Ildar", "Fitkulin", true);
        book.addGrades(1, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.addGrades(1, "assignment", new ArrayList<>((Arrays.asList(Mark.GOOD, Mark.GOOD, Mark.GOOD))));
        book.addGrades(1, "colloquium", new ArrayList<>((Arrays.asList(Mark.SATISFACTORY, Mark.EXCELLENT, Mark.GOOD))));
        book.addGrades(1, "test", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.addGrades(2, "assignment", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.UNSATISFACTORY, Mark.EXCELLENT))));
        book.addGrades(2, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.UNSATISFACTORY, Mark.EXCELLENT))));
        book.addGrades(3, "exam", new ArrayList<>((Arrays.asList(Mark.EXCELLENT, Mark.EXCELLENT, Mark.SATISFACTORY))));
        book.addGrades(4, "exam", new ArrayList<>((Arrays.asList(Mark.GOOD, Mark.EXCELLENT, Mark.EXCELLENT))));
        book.setQualificationWork(4);
        System.out.println(book.calculateAverage());
        System.out.println(book.isItBudget());
        System.out.println(book.isHonorDegree());
        System.out.println(book.higherSchoolarship());
        System.out.println(book.getFullName());

    }
}