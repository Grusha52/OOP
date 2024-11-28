package ru.nsu.chernikov;

public enum Mark {

    EXCELLENT("Отлично", 5),
    GOOD("Хорошо", 4),
    SATISFACTORY("Удовлитворительно", 3),
    UNSATISFACTORY("Неудовлетворительно", 2),
    GRADED("Зачёт", null),
    FAIL("Незачёт", null);


    Mark(String string, Integer integer) {
        this.markint = integer;
        this.markstr = string;
    };

//    Mark(String string) {
//        this.markstr = string;
//        switch (string) {
//            case "Отлично" -> this.markint = 5;
//            case "Хорошо" -> this.markint = 4;
//            case "Удовлетворительно" -> this.markint = 3;
//            case "Неудовлетворительно" -> this.markint = 2;
//            case "Зачет" -> this.markint = null;
//            case "Не сдал" -> this.markint = null;
//            default -> throw new IllegalArgumentException("Unknown mark: " + markstr);
//        }
//    }

//    Mark(Integer mark) {
//        this.markint = mark;
//        switch (mark) {
//            case 5 -> this.markstr = "Отлично";
//            case 4 -> this.markstr = "Хорошо";
//            case 3 -> this.markstr = "Удовлетворительно";
//            case 2 -> this.markstr = "Неудовлетворительно";
//            default -> throw new IllegalArgumentException("Unknown mark: " + mark);
//        }
//    }

    String markstr;
    Integer markint;

    public int intValue() {
        return markint != null ? markint : 0; // Возвращает 0, если числовое значение отсутствует
    }
}
