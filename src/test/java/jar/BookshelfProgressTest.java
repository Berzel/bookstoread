package jar;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("Bookshelf progress ")
@ExtendWith(BooksParameterResolver.class)
public class BookshelfProgressTest {

  Bookshelf bookshelf;
  Book database;
  Book forex;
  Book hustler;
  Book javaInAction;

  @BeforeEach
  public void init(Map<String, Book> books) {
    this.bookshelf = new Bookshelf();
    this.database = books.get("Database Design");
    this.forex = books.get("Forex for beginners");
    this.hustler = books.get("Hustlers Bible");
    this.javaInAction = books.get("Java in Action");
  }

  @Test
  @DisplayName(value = "should be 0 progress when no books in shelf")
  public void should_beZeroProgessWhenNoBooksInShelf() {
    assertThat(this.bookshelf.progress().toDo()).isEqualTo(0);
    assertThat(this.bookshelf.progress().inProgress()).isEqualTo(0);
    assertThat(this.bookshelf.progress().isComplete()).isEqualTo(0);
  }

  @Test
  @DisplayName(value = "should be 100% todo, 0% in progress, 0% complete when no book is read")
  public void should_beZeroCompleteWhenNoBookRead() {
    this.bookshelf.add(database, forex, hustler);
    assertThat(this.bookshelf.progress().toDo()).isEqualTo(100);
    assertThat(this.bookshelf.progress().inProgress()).isEqualTo(0);
    assertThat(this.bookshelf.progress().isComplete()).isEqualTo(0);
  }

  @Test
  @DisplayName(value = "should be 75% todo, 25% in progess, 0% complete when reading one book")
  public void should_be25inProgessWhenReadingOneBook() {
    this.bookshelf.add(database, forex, javaInAction, hustler);
    this.database.startedReadingAt(LocalDate.of(2016, Month.JULY, 1));

    assertThat(this.bookshelf.progress().toDo()).isEqualTo(75);
    assertThat(this.bookshelf.progress().inProgress()).isEqualTo(25);
    assertThat(this.bookshelf.progress().isComplete()).isEqualTo(0);
  }

  @Test
  @DisplayName(value = "should be 75% todo, 0% in progess, 25% complete when one book read")
  public void should_be25CompleteWhenOneBookRead() {
    this.bookshelf.add(database, forex, javaInAction, hustler);
    this.database.startedReadingAt(LocalDate.of(2016, Month.JULY, 1));
    this.database.finishedReadingAt(LocalDate.of(2016, Month.AUGUST, 1));

    assertThat(this.bookshelf.progress().toDo()).isEqualTo(75);
    assertThat(this.bookshelf.progress().inProgress()).isEqualTo(0);
    assertThat(this.bookshelf.progress().isComplete()).isEqualTo(25);
  }

  @Test
  @DisplayName(value = "should be 50% todo, 25% in progess, 25% complete when one book read, and one in progress")
  public void should_be25CompleteAnd25InProgess() {
    this.bookshelf.add(database, forex, javaInAction, hustler);
    this.database.startedReadingAt(LocalDate.of(2016, Month.JULY, 1));
    this.database.finishedReadingAt(LocalDate.of(2016, Month.AUGUST, 1));
    this.forex.startedReadingAt(LocalDate.of(2016, Month.AUGUST, 2));

    assertThat(this.bookshelf.progress().toDo()).isEqualTo(50);
    assertThat(this.bookshelf.progress().inProgress()).isEqualTo(25);
    assertThat(this.bookshelf.progress().isComplete()).isEqualTo(25);
  }
}