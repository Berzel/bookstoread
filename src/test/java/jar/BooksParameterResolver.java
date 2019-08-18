package jar;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class BooksParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    Parameter parameter = parameterContext.getParameter();
    return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, jar.Book>");
  }

  @Override
  public Map<String, Book> resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) 
    throws ParameterResolutionException {
      Map<String, Book> books = new HashMap<>();
      books.put("Database Design", new Book("Database Design", "Rod Stevens", LocalDate.of(2018, 1, 25)));
      books.put("Forex for beginners", new Book("Forex for beginners", "Anna Coulling", LocalDate.of(2012, 8, 4)));
      books.put("Hustlers Bible", new Book("Hustlers Bible", "Gayton McKenzie", LocalDate.of(2011, 3, 10)));
      books.put("Java in Action", new Book("Java in Action", "Berzel Best", LocalDate.of(2012, 5, 6)));
      books.put("Module Design", new Book("Module Design", "Berzel Best", LocalDate.of(2016, 1, 25)));
      return books;
	}
}