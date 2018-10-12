package source;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class JsonDataSourceTest {
    private JsonDataSource jsonDataSource = new JsonDataSource();

    @Test
    void should_return_collectionOfGivenSize() {
        var size = 5;

        Collection<String> fetchResult = jsonDataSource.fetch(size);

        assertThat(fetchResult).hasSize(size);
    }
}