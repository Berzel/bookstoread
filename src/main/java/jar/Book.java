package jar;

import java.time.LocalDate;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDate getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(LocalDate publishedAt) {
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