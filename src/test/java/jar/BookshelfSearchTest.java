package jar;

import static jar.AuthorFilter.byAuthor;
import static jar.YearAfterFilter.yearAfter;
import static jar.YearBeforeFilter.yearBefore;
import static jar.YearFilter.byYear;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName(value = "Bookshelf search ")
@ExtendWith(BooksParameterResolver.class)
public class BookshelfSearchTest {

  Bookshelf bookshelf;
  Book database;
  Book forex;
  Book hustler;
  Book javaInAction;
  Book moduleDesign;

  @BeforeEach
  public void init(Map<String, Book> books) {
    this.bookshelf = new Bookshelf();
    this.database = books.get("Database Design");
    this.forex = books.get("Forex for beginners");
    this.hustler = books.get("Hustlers Bible");
    this.javaInAction = books.get("Java in Action");
    this.moduleDesign = books.get("Module Design");
    this.bookshelf.add(database, forex, hustler, javaInAction, moduleDesign);
  }

  @Test
  @DisplayName(value = "should find books containing provided text")
  public void should_findBooksContainingProvidedText() {
    assertThat(this.bookshelf.search("forex")).contains(forex);
    assertThat(this.bookshelf.search("beginner").size()).isEqualTo(1);
  }

  @Test
  @DisplayName(value = "should filter search results by provided author")
  public void should_filterSearchResultsByProvidedAuthor() {
    assertThat(this.bookshelf.search("design")).contains(database, moduleDesign);
    assertThat(this.bookshelf.search("design", byAuthor("Berzel Best")).size()).isEqualTo(1);
  }

  @Test
  @DisplayName(value = "should filter search results by year")
  public void should_filterSearchResultsByYear() {
    assertThat(this.bookshelf.search("code", byYear(2016)).size()).isEqualTo(0);
    assertThat(this.bookshelf.search("java", byYear(2012)).size()).isEqualTo(1);
  }

  @Test
  @DisplayName(value = "should filter search results by year after")
  public void should_filterSearchByYearAfter() {
    assertThat(this.bookshelf.search("design", yearAfter(2017)).size()).isEqualTo(1);
    assertThat(this.bookshelf.search("design", yearAfter(2015)).size()).isEqualTo(2);
    assertThat(this.bookshelf.search("design", yearAfter(2019)).size()).isEqualTo(0);
  }

  @Test
  @DisplayName(value = "should be able to determine the author and year of the book")
  public void should_determineAuthor() {
    assertThat(byAuthor("Berzel Best").apply(hustler)).isFalse();
    assertThat(byAuthor("Rod Stevens").apply(database)).isTrue();
    assertThat(byAuthor("Berzel Best").apply(moduleDesign)).isTrue();

    assertThat(byYear(2012).apply(javaInAction)).isTrue();
    assertThat(byYear(2012).apply(forex)).isTrue();
    assertThat(byYear(2012).apply(database)).isFalse();

    assertThat(yearAfter(2012).apply(database)).isTrue();
    assertThat(yearBefore(2011).apply(forex)).isFalse();
  }

  @Test
  @DisplayName(value = "should filter using multiple criteria")
  public void should_filterUsingMultipleCriteria() {
    CompositeFilter filter = new CompositeFilter();
    filter.add(byAuthor("Rod Stevens")).add(byYear(2018)).add(yearAfter(2011));
    assertThat(this.bookshelf.search("design", filter).size()).isEqualTo(1);
  }
}