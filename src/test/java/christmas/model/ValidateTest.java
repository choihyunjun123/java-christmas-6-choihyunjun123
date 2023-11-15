package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    Validate validate = new Validate();

    @DisplayName("날짜 예외 검증.")
    @ParameterizedTest
    @CsvSource(value = {"1:1", "15:15"}, delimiter = ':')
    void validateDate(String date, int expect) {
        int result = validate.validateDate(date);
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("날짜 입력에 문자 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"a", "-", "/", "ㅁ"})
    void number(String date) {
        assertThatThrownBy(() -> validate.validateNumber(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력에 달력에 없는 숫자 입력하면 예외가 발생한다.")
    @Test
    void range() {
        assertThatThrownBy(() -> validate.validateRange("45"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateOrder() {
        HashMap<String, Integer> menuAndNumber = new HashMap<>();
        menuAndNumber.put("양송이스프", 1);
        menuAndNumber.put("티본스테이크", 2);
        menuAndNumber.put("초코케이크", 3);
        menuAndNumber.put("제로콜라", 4);
        HashMap<String, Integer> result = new HashMap<>();
        result = validate.validateOrder("양송이스프-1,티본스테이크-2,초코케이크-3,제로콜라-4");
        assertThat(result).isEqualTo(menuAndNumber);
    }

    @DisplayName("주문 입력 형식 오류시 예외가 발생한다.")
    @Test
    void form() {
        assertThatThrownBy(() -> validate.orderForm(List.of(List.of("양송이스프1"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 입력 개수에 문자 입력시 예외가 발생한다.")
    @Test
    void orderNumberWord() {
        assertThatThrownBy(() -> validate.orderNumberWord(List.of(List.of("양송이스프", "a"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1개 이상 주문 안 하면 예외가 발생한다.")
    @Test
    void orderNumberRange() {
        assertThatThrownBy(() -> validate.orderNumberRange(List.of(List.of("양송이스프", "0"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void duplication() {
        assertThatThrownBy(() -> validate.orderDuplication(List.of(List.of("양송이스프", "1"), List.of("양송이스프", "2"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("없는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void menuExist() {
        HashMap<String, Integer> menuAndNumber = new HashMap<>();
        menuAndNumber.put("치즈케이크", 1);
        assertThatThrownBy(() -> validate.menuExist(menuAndNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하면 예외가 발생한다.")
    @Test
    void drinkOnly() {
        HashMap<String, Integer> menuAndNumber = new HashMap<>();
        menuAndNumber.put("제로콜라", 1);
        assertThatThrownBy(() -> validate.drinkOnly(menuAndNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("20개 이상 주문하면 예외가 발생한다.")
    @Test
    void totalOrderNum() {
        HashMap<String, Integer> menuAndNumber = new HashMap<>();
        menuAndNumber.put("양송이스프", 21);
        assertThatThrownBy(() -> validate.totalOrderNum(menuAndNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}