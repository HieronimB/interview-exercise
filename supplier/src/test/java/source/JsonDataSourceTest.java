package source;

import org.junit.jupiter.api.Test;
import random.RandomGenerator;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonDataSourceTest {
    private RandomGenerator randomGenerator = new RandomGenerator();
    private JsonDataSource jsonDataSource = new JsonDataSource(randomGenerator);

    @Test
    void should_return_collectionOfGivenSize() {
        var size = 5;

        Collection<String> fetchResult = jsonDataSource.fetch(size);

        assertThat(fetchResult).hasSize(size);
    }

    @Test
    void should_return_emptyListWhenPassedZeroAsArgument() {
        List<String> fetchResult = jsonDataSource.fetch(0);

        assertThat(fetchResult).isEmpty();
    }

    @Test
    void should_return_emptyListWhenPassedNegativeNumberAsArgument() {
        List<String> fetchResult = jsonDataSource.fetch(-1);

        assertThat(fetchResult).isEmpty();
    }
}