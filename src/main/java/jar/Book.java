package jar;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Book {

  private String title;
  private String author;
  private LocalDate publishedAt;

  private LocalDate startedReadingAt;
  private LocalDate finishedReadingAt;

  public Book(String title, String author, LocalDate publishedAt) {
    this.title = title;
    this.author = author;
    this.publishedAt = publishedAt;
  }

  @Override
  public String toString() {
    return "Book [title=" + title + ", author=" + author + ", publishedAt=" + publishedAt + "]";
  }

  public void startedReadingAt(LocalDate startedReadingAt) {
    this.startedReadingAt = startedReadingAt;
  }

  public void finishedReadingAt(LocalDate finishedReadingAt) {
    if (this.startedReadingAt == null) {
      throw new RuntimeException("Book not started");
   }

    this.finishedReadingAt = finishedReadingAt;
  }

  public boolean inProgress () {
    return this.startedReadingAt != null && this.finishedReadingAt == null;
  }

  public boolean isRead () {
    return this.startedReadingAt != null && this.finishedReadingAt != null;
  }
}