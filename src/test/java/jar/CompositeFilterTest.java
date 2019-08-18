package jar;

import static jar.AuthorFilter.byAuthor;
import static jar.YearAfterFilter.yearAfter;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Composite filter ")
@ExtendWith(BooksParameterResolver.class)
public class CompositeFilterTest implements FilterBoundaryTests {

  CompositeFilter filter;
  Book database;
  Book javaInAction;

  @Override
  public BookFilter get() {
    return filter;
  }

  @BeforeEach
  public void init(Map<String, Book> books) {
    filter = new CompositeFilter();
    filter.add(yearAfter(2011)).add(byAuthor("Berzel Best"));

    javaInAction = books.get("Java in Action");
    database = books.get("Database Design");
  }

  @Test
  @DisplayName(value = "should determine both author and year of book")
  public void test() {
    assertThat(get().apply(database)).isFalse();
    assertThat(get().apply(javaInAction)).isTrue();
  }
}