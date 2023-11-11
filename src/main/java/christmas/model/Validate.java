package christmas.model;

public class Validate {
    private static final String INPUT_CONTENT_REGEX = "^[0-9]+$";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;

    public void validateDate(String date) {
        number(date);
        range(date);
    }

    public void number(String numbers) {
        if (!numbers.matches(INPUT_CONTENT_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    public void range(String numbers) {
        if (Integer.parseInt(numbers) < MONTH_START_DATE || Integer.parseInt(numbers) > MONTH_END_DATE) {
            throw new IllegalArgumentException("[ERROE] 날짜 입력 오류 입니다.");
        }
    }
}
