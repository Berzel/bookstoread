package jar;

public class Progress {

  private final int toRead;
  private final int inProgress;
  private final int completed;

  public Progress(int toRead, int inProgress, int completed) {
    this.toRead = toRead;
    this.inProgress = inProgress;
    this.completed = completed;
  }

  public int isComplete() {
    return this.completed;
  }

  public int toDo() {
    return this.toRead;
  }

  public int inProgress() {
    return this.inProgress;
  }
}