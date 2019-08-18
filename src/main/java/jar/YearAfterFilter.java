package jar;

import static java.time.Year.of;

public class YearAfterFilter implements BookFilter {

  private int year;

  public static YearAfterFilter yearAfter(int year) {
    YearAfterFilter filter = new YearAfterFilter();
    filter.year = year;
    return filter;
  }

  @Override
  public boolean apply(Book book) {
    return book != null && of(book.getPublishedAt().getYear()).isAfter(of(year));
  }
}