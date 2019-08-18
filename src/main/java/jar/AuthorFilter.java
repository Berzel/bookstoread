package jar;

/**
 * AuthorFilter
 */
public class AuthorFilter implements BookFilter {

  private String name;

  public static AuthorFilter byAuthor (String author) {
    AuthorFilter filter = new AuthorFilter();
    filter.name = author;
    return filter;
  }

  @Override
  public boolean apply(Book book) {
    return book != null && book.getAuthor().equalsIgnoreCase(name);
  }
}