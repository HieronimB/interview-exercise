package random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
    public String next(GeneratorStrategy strategy){ // Wcięło spację przed klamrą
        return strategy.next();
    }
}
