package source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import random.Generators;
import random.RandomGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class JsonDataSource implements DataSource<String> {
    private RandomGenerator randomGenerator;

    @Autowired
    public JsonDataSource(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public List<String> fetch(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> fetchJson())
                .collect(Collectors.toList());
    }

    private String fetchJson() {
        return String.format("{ _type: \"%1$s\", " +
                             "_id: %2$s, " +
                             "key: %2$s, " +
                             "name: \"%1$s\", " +
                             "fullName: \"%1$s\", " +
                             "iata_airport_code: %2$s, " +
                             "type: \"%1$s\", " +
                             "country: \"%1$s\", " +
                             "geo_position: { latitude: %2$s, longitude: %2$s }, " +
                             "location_id: %2$s, " +
                             "inEurope: %3$s, " +
                             "countryCode: \"%4$s\", " +
                             "coreCountry: %3$s, " +
                             "distance: %2$s }",
                randomGenerator.next(Generators.STRING),
                randomGenerator.next(Generators.INTEGER),
                randomGenerator.next(Generators.BOOL),
                randomGenerator.next(Generators.COUNTRY_CODE));
    }
}
