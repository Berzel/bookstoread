package jar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Every filter ")
public interface FilterBoundaryTests {

  BookFilter get();

  @Test
  @DisplayName(value = "should return false for null book")
  default public void should_returnFalseForNullBook() {
    assertThat(get().apply(null)).isFalse();
  }
}