package jar;

import static jar.BookSorter.byTitle;
import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Bookshelf {

  private List<Book> books = new ArrayList<>();

	public void add(Book... booksToAdd) {
    stream(booksToAdd).forEach(book -> {
      if (!this.books.contains(book)) {
        this.books.add(book);
      }
    });
  }
  
	public List<Book> getBooks() {
		return unmodifiableList(this.books);
  }
  
	public List<Book> sort() {
    return this.sort(byTitle());
	}

	public List<Book> sort(Comparator<Book> creteria) {
		return unmodifiableList(this.books.stream().sorted(creteria).collect(toList()));
	}

	public Map<Year, List<Book>> groupByYear() {
		return this.groupBy(book -> Year.of(book.getPublishedAt().getYear()));
  }
  
  public <T> Map<T, List<Book>> groupBy(Function<Book, T> fx) {
		return this.books.stream().collect(groupingBy(fx));
	}

  public Progress progress() {
    if (this.books.size() > 0) {
      int booksInProgress = Long.valueOf(this.books.stream().filter(Book::inProgress).count()).intValue();
      int percentInProgress = booksInProgress * 100 / this.books.size();

      int booksRead = Long.valueOf(this.books.stream().filter(Book::isRead).count()).intValue();
      int percentRead = booksRead * 100 / this.books.size();

      int booksToRead = this.books.size() - (booksInProgress + booksRead);
      int percentToRead = booksToRead * 100 / this.books.size();

      return new Progress(percentToRead, percentInProgress, percentRead);
    }

    return new Progress(0, 0, 0);
  }

  public List<Book> search(String search) {
    return this.search(search, book -> true);
  }
  
  public List<Book> search(String search, BookFilter filter){
    return this.books.stream()
      .filter(book -> book.getTitle().toLowerCase().contains(search.toLowerCase()))
      .filter(book -> filter.apply(book)).collect(toList());
  }
}