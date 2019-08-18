package jar;

import static jar.YearAfterFilter.yearAfter;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Year after filter ")
@ExtendWith(BooksParameterResolver.class)
public class YearAfterFilterTest implements FilterBoundaryTests {

  BookFilter filter;
  Book database;

  @Override
  public BookFilter get() {
    return filter;
  }

  @BeforeEach
  public void init(Map<String, Book> books) {
    filter = yearAfter(2011);
    database = books.get("Database Design");
  }

  @Test
  @DisplayName(value = "should return false")
  public void shouldReturnFalse() {
    assertThat(get().apply(database)).isTrue();
  }
}