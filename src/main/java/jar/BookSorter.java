package jar;

import java.util.Comparator;

public class BookSorter  {

  public static Comparator<Book> byAuthor() {
    return new AuthorSorter();
  }

  public static class AuthorSorter implements Comparator<Book> {
    @Override
    public int compare(Book fBook, Book sBook) {
      return fBook.getAuthor().compareTo(sBook.getAuthor());
    }
  }

  public static Comparator<Book> byTitle() {
    return new TitleSorter();
  }

  public static class TitleSorter implements Comparator<Book> {
    @Override
    public int compare(Book fBook, Book sBook) {
      return fBook.getTitle().compareTo(sBook.getTitle());
    }
  }
}