package ru.nsu.chernikov;

import java.util.*;

public class RecordBook {
    private String firstname;
    private String lastname;
    Map<Integer, Map<String, ArrayList<Integer>>> grades;
    private Integer qualificationWork;
    private Boolean paidEducation;

    private static final String ASSIGNMENT = "assignment";
    private static final String TEST = "test";
    private static final String COLLOQUIUM = "colloquium";
    private static final String EXAM = "exam";
    private static final String DIFFERENTIATED_TEST = "differentiated_test";
    private static final String CREDIT = "credit";
    private static final String PRACTICE_REPORT = "assignment";
    private static final String QUALIFICATION_WORK = "qualification_work";

    public RecordBook(String firstname, String lastname, Boolean paidEducation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.paidEducation = paidEducation;
        this.grades = new HashMap<>();
        this.qualificationWork = null;
    }

    public void addGrades(int semester, String type, ArrayList<Integer> listOfGrades) {
        grades.putIfAbsent(semester, new HashMap<>());
        grades.get(semester).putIfAbsent(type, new ArrayList<>());
        grades.get(semester).get(type).addAll(listOfGrades);
    }

    public double calculateAverage() {
        ArrayList<Integer> allGrades = new ArrayList<>();
        for (Map<String, ArrayList<Integer>> semester : grades.values()) {
            for (ArrayList<Integer> grades : semester.values()) {
                allGrades.addAll(grades);
            }
        }
        return allGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public Boolean isHonorDegree() {
        ArrayList<Integer> allGrades = new ArrayList<>();
        for (Map<String, ArrayList<Integer>> semester : grades.values()) {
            for (ArrayList<Integer> grades : semester.values()) {
                allGrades.addAll(grades);
            }
        }
        if (allGrades.stream().anyMatch(grade -> grade == 3)) {
            return false;
        }
        long excellentCount = allGrades.stream().filter(grade -> grade == 5).count();
        if ((double) excellentCount / allGrades.size() < 0.75) {
            return false;
        }
        return qualificationWork == 5;
    }

    public boolean isItBudget() {
        if (paidEducation) {
            ArrayList<Integer> semesters = new ArrayList<>(grades.keySet());
            semesters.sort(Collections.reverseOrder());
            List<Integer> lastTwoSemesters = semesters.subList(0, Math.min(2, semesters.size()));
            for (int semester : lastTwoSemesters) {
                Map<String, ArrayList<Integer>> semesterGrades = grades.get(semester);
                if (semesterGrades.containsKey(EXAM)) {
                    if (semesterGrades.get(EXAM).stream().anyMatch(grade -> grade == 3)) {
                        return false;
                    }
                }
                if (semesterGrades.containsKey(DIFFERENTIATED_TEST)) {
                    if (semesterGrades.get(DIFFERENTIATED_TEST).stream().anyMatch(grade -> grade == 3)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean higherSchoolarship(int semester) {
        return false;
    }

    public void setQualificationWork(int grade) {
        this.qualificationWork = grade;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
}