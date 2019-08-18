package jar;

import static java.time.Year.of;

public class YearBeforeFilter implements BookFilter {

  private int year;

  public static YearBeforeFilter yearBefore(int year) {
    YearBeforeFilter filter = new YearBeforeFilter();
    filter.year = year;
    return filter;
  }

  @Override
  public boolean apply(Book book) {
    return book != null && of(book.getPublishedAt().getYear()).isBefore(of(year));
  }
}