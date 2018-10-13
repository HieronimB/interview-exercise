package random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
    public String next(GeneratorStrategy strategy){
        return strategy.next();
    }
}
