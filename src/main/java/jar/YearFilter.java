package jar;

public class YearFilter implements BookFilter {

  private int year;

  public static YearFilter byYear (int year) {
    YearFilter filter = new YearFilter();
    filter.year = year;
    return filter;
  }

  @Override
  public boolean apply(Book book) {
    return book != null && book.getPublishedAt().getYear() == year;
  }
}