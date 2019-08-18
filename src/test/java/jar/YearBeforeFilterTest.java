package jar;

import static jar.YearBeforeFilter.yearBefore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Year before filter ")
@ExtendWith(BooksParameterResolver.class)
public class YearBeforeFilterTest implements FilterBoundaryTests {

  BookFilter filter;
  Book database;

  @Override
  public BookFilter get() {
    return filter;
  }

  @BeforeEach
  public void init(Map<String, Book> books) {
    filter = yearBefore(2019);
    database = books.get("Database Design");
  }

  @Test
  @DisplayName(value = "should determine if book is published before specified year")
  public void shouldReturnFalse() {
    assertThat(get().apply(database)).isTrue();
  }
}