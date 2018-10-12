package source;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonDataSource implements DataSource<String> {
    @Override
    public List<String> fetch(int size) {
        return IntStream.range(0, size)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
