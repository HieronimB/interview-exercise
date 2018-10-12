package source;

import java.util.Collection;

public interface DataSource<T> {
    Collection<T> fetch(int size);
}
