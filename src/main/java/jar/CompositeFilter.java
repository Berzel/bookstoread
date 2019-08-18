package jar;

import java.util.ArrayList;
import java.util.List;

public class CompositeFilter implements BookFilter {

  private List<BookFilter> filters;

  public CompositeFilter() {
    this.filters = new ArrayList<>();
  }  

  public CompositeFilter add(BookFilter filter) {
    this.filters.add(filter);
    return this;
  }

  @Override
  public boolean apply(Book book) {
    return book != null && filters.stream().map(filter -> filter.apply(book)).reduce(true, (a, b) -> a && b);
  }
}