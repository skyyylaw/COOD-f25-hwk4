import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {
  private Source source = new Source() {
    @Override
    public List<String> get() {
      return List.of("Hello", "World");
    }
  };
  List<String> filterList = List.of("hello", "world");

  @Test
  public void nullInputList() {
    assertNull(Filter.filter(null, source));
  }

  @Test
  public void nullSource() {
    assertNull(Filter.filter(filterList, null));
  }

  @Test
  public void emptyInputList() {
    assertEquals(Collections.emptySet(), Filter.filter(Collections.emptyList(), source));
  }

  @Test
  public void getReturnsEmptyList() {
    assertEquals(Collections.emptySet(), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return Collections.emptyList();
      }
    }));
  }

  @Test
  public void getReturnsNull() {
    assertEquals(Collections.emptySet(), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return null;
      }
    }));
  }

  @Test
  public void filterReturnSingleString1() {
    assertEquals(Set.of("hello"), Filter.filter(List.of("hello"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }

  @Test
  public void filterReturnSingleString2() {
    assertEquals(Set.of("hello"), Filter.filter(List.of("hello", "world"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "and", "you");
      }
    }));
  }

  @Test
  public void filterReturnSingleString3() {
    assertEquals(Set.of("hello"), Filter.filter(List.of("hello", "world"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello");
      }
    }));
  }

  @Test
  public void filterReturnMultipleString() {
    assertEquals(Set.of("hello", "world"), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }


  @Test
  public void filterReturnEmpty() {
    assertEquals(Collections.emptySet(), Filter.filter(List.of("WOOHOO"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }
}
