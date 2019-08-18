package jar;

import static jar.YearFilter.byYear;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Year filter ")
@ExtendWith(BooksParameterResolver.class)
public class YearFilterTest implements FilterBoundaryTests {

  BookFilter filter;
  Book database;

  @Override
  public BookFilter get() {
    return filter;
  }

  @BeforeEach
  public void init(Map<String, Book> books) {
    filter = byYear(2018);
    database = books.get("Database Design");
  }

  @Test
  @DisplayName(value = "should determine if book is published in a certain year")
  public void shouldReturnFalse() {
    assertThat(get().apply(database)).isTrue();
  }
}