package jar;

import static jar.AuthorFilter.byAuthor;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Author filter ")
@ExtendWith(BooksParameterResolver.class)
public class AuthorFilterTest implements FilterBoundaryTests {

  BookFilter filter;
  Book javaInAction;

  @Override
  public BookFilter get() {
    return filter;
  }

  @BeforeEach
  public void init(Map<String, Book> books) {
    filter = byAuthor("Berzel Best");
    javaInAction = books.get("Java in Action");
  }

  @Test
  @DisplayName(value = "should determine the author of the book")
  public void should_determineAuthorOfBook() {
    assertThat(get().apply(javaInAction)).isTrue();
  }
}