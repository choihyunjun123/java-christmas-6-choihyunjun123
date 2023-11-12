package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventBadgeTest {

    @DisplayName("배지를 리턴한다")
    @ParameterizedTest
    @CsvSource(value = {"30000:산타", "15000:트리", "7000:별"}, delimiter = ':')
    void presentBadge(int price, String badge) {
        EventBadge eventBadge = new EventBadge();
        assertThat(eventBadge.presentBadge(price)).isEqualTo(badge);
    }
}