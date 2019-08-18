package jar;

import static jar.BookSorter.byAuthor;
import static jar.BookSorter.byTitle;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Year;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Bookshelf ")
@ExtendWith(BooksParameterResolver.class)
public class BookshelfTest {

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
  @DisplayName(value = "should be empty when no books added")
  public void should_beEmptyWhenNoBooksAdded() {
    assertThat(this.bookshelf.getBooks()).isEmpty();
  }

  @Test
  @DisplayName(value = "should have two books when only two books added")
  public void should_haveTwoBooksWhenTwoBooksAdded() {
    this.bookshelf.add(database, forex);
    assertThat(this.bookshelf.getBooks().size()).isEqualTo(2);
  }

  @Test
  @DisplayName(value = "should contain the added books")
  public void should_containAddedBooks() {
    this.bookshelf.add(database, forex);
    assertThat(this.bookshelf.getBooks()).contains(database, forex);
  }

  @Test
  @DisplayName(value = "should not add a book that already exists")
  public void should_notAddABookThatAlreadyExists() {
    this.bookshelf.add(database, forex);
    this.bookshelf.add(database, forex);
    this.bookshelf.add(forex);
    assertThat(this.bookshelf.getBooks().size()).isEqualTo(2);
  }

  @Test
  @DisplayName(value = "should be empty when add is called without args")
  public void should_beEmptyWhenAddHasNoBooks() {
    this.bookshelf.add();
    assertThat(this.bookshelf.getBooks()).isEmpty();
  }

  @Test
  @DisplayName(value = "should not allow to modify the books returned by getBooks()")
  public void should_throwAnExceptionWhenModifyBooksReturned() {
    assertThatThrownBy(() -> this.bookshelf.getBooks().add(database)).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  @DisplayName(value = "should sort books by title")
  public void should_arrangeBooksByTitle() {
    this.bookshelf.add(database, hustler, forex);
    assertThat(this.bookshelf.sort()).isSortedAccordingTo(byTitle());
    assertThat(this.bookshelf.sort(byTitle().reversed())).isSortedAccordingTo(byTitle().reversed());
  }

  @Test
  @DisplayName(value = "should not alter the original arrangment order")
  public void should_notAlterOriginalOrderAfterArranging() {
    this.bookshelf.add(database, hustler, forex);
    this.bookshelf.sort();
    assertThat(this.bookshelf.getBooks()).isEqualTo(asList(database, hustler, forex));
  }

  @Test
  @DisplayName(value = "should sort by author")
  public void should_sortByAuthor() {
    this.bookshelf.add(database, hustler, forex);
    assertThat(this.bookshelf.sort(byAuthor())).isSortedAccordingTo(byAuthor());
    assertThat(this.bookshelf.sort(byAuthor().reversed())).isSortedAccordingTo(byAuthor().reversed());
  }

  @Test
  @DisplayName(value = "should group books by year")
  public void should_groupBooksByYear() {
    this.bookshelf.add(database, javaInAction, hustler, forex);
    Map<Year, List<Book>> groupedByYear = this.bookshelf.groupByYear();

    assertThat(groupedByYear).containsKey(Year.of(2018)).containsValues(asList(database));
    assertThat(groupedByYear).containsKey(Year.of(2011)).containsValues(asList(hustler));
    assertThat(groupedByYear).containsKey(Year.of(2012)).containsValues(asList(javaInAction, forex));
  }

  @Test
  @DisplayName(value = "should group books by user provided criteria")
  public void should_groupByProvidedCriteria() {
    this.bookshelf.add(database, javaInAction, hustler, forex);
    Map<String, List<Book>> groupedByAuthor = this.bookshelf.groupBy(Book::getAuthor);

    assertThat(groupedByAuthor).containsKeys("Berzel Best").containsValues(asList(javaInAction));
    assertThat(groupedByAuthor).containsKeys("Anna Coulling").containsValues(asList(forex));
    assertThat(groupedByAuthor).containsKeys("Rod Stevens").containsValues(asList(database));
    assertThat(groupedByAuthor).containsKeys("Gayton McKenzie").containsValues(asList(hustler));
  }


}