package ru.nsu.chernikov;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recordbook.
 */
public class RecordBook {
    private String firstname;
    private String lastname;
    Map<Integer, Map<String, ArrayList<Mark>>> grades;
    private Integer qualificationWork;
    private Boolean paidEducation;

    private static final String ASSIGNMENT = "assignment";
    private static final String TEST = "test";
    private static final String COLLOQUIUM = "colloquium";
    private static final String EXAM = "exam";
    private static final String DIFFERENTIATED_TEST = "differentiated_test";
    private static final String CREDIT = "credit";
    private static final String PRACTICE_REPORT = "practice_report";
    private static final String QUALIFICATION_WORK = "qualification_work";

    /**
     * Constructor of RecordBook.
     *
     * @param firstname     student's firstname
     * @param lastname      student's lastname
     * @param paidEducation budget or not
     */
    public RecordBook(String firstname, String lastname, Boolean paidEducation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.paidEducation = paidEducation;
        this.grades = new HashMap<>();
        this.qualificationWork = null;
    }

    /**
     * Add grades method.
     *
     * @param semester     which semester.
     * @param type         type of mark.
     * @param listOfGrades list of marks.
     */
    public void addGrades(int semester, String type, ArrayList<Mark> listOfGrades) {
        grades.putIfAbsent(semester, new HashMap<>());
        grades.get(semester).putIfAbsent(type, new ArrayList<>());
        grades.get(semester).get(type).addAll(listOfGrades);
    }

    /**
     * Average calculation.
     *
     * @return average.
     */
    public double calculateAverage() {
        ArrayList<Mark> allGrades = new ArrayList<>();
        for (Map<String, ArrayList<Mark>> semester : grades.values()) {
            for (ArrayList<Mark> grades : semester.values()) {
                allGrades.addAll(grades);
            }
        }
        return allGrades.stream().mapToInt(Mark::intValue).average().orElse(0.0);
    }


    /**
     * Honor degree check.
     *
     * @return true of false
     */
    public Boolean isHonorDegree() {
        ArrayList<Mark> allGrades = new ArrayList<>();
        for (Map<String, ArrayList<Mark>> semester : grades.values()) {
            if ((semester.containsKey(EXAM)
                    && semester.get(EXAM).stream().anyMatch(grade -> grade == Mark.SATISFACTORY))
                    || (semester.containsKey(DIFFERENTIATED_TEST)
                    && semester.get(DIFFERENTIATED_TEST).stream().anyMatch(grade -> grade == Mark.UNSATISFACTORY))) {
                return false;
            }

            for (ArrayList<Mark> grades : semester.values()) {
                allGrades.addAll(grades);

            }
        }
        if (allGrades.stream().anyMatch(grade -> grade == Mark.SATISFACTORY)) {
            return false;
        }
        long excellentCount = allGrades.stream().filter(grade -> grade == Mark.EXCELLENT).count();
        if ((double) excellentCount / allGrades.size() < 0.75) {
            return false;
        }
        return qualificationWork == 5;
    }

    /**
     * Can it be budget.
     *
     * @return true of false
     */
    public boolean isItBudget() {
        if (paidEducation) {
            ArrayList<Integer> semesters = new ArrayList<>(grades.keySet());
            semesters.sort(Collections.reverseOrder());
            List<Integer> lastTwoSemesters = semesters.subList(0, Math.min(2, semesters.size()));
            for (int semester : lastTwoSemesters) {
                Map<String, ArrayList<Mark>> semesterGrades = grades.get(semester);
                if (semesterGrades.containsKey(EXAM)) {
                    if (semesterGrades.get(EXAM).stream().anyMatch(grade -> grade == Mark.SATISFACTORY)) {
                        return false;
                    }
                }
                if (semesterGrades.containsKey(DIFFERENTIATED_TEST)) {
                    if (semesterGrades.get(DIFFERENTIATED_TEST).stream().anyMatch(
                            grade -> grade == Mark.UNSATISFACTORY)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Higher scholarship detection.
     *
     * @return true or false
     */
    public boolean higherSchoolarship() {
        ArrayList<Integer> semesters = new ArrayList<>(grades.keySet());
        Integer lastSemester = semesters.getLast();
        if (!grades.containsKey(lastSemester)) {
            return false;
        }
        Map<String, ArrayList<Mark>> semesterGrades = grades.get(lastSemester);
        return semesterGrades.values().stream()
                .flatMap(Collection::stream)
                .allMatch(grade -> grade == Mark.EXCELLENT);
    }

    /**
     * set grade qualification work.
     *
     * @param grade mark of qualification work
     */
    public void setQualificationWork(int grade) {
        this.qualificationWork = grade;
    }

    /**
     * get full name of student.
     *
     * @return full name
     */
    public String getFullName() {
        return firstname + " " + lastname;
    }
}