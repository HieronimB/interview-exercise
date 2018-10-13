package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import random.RandomGenerator;
import rest.SupplierController;
import source.JsonDataSource;

@Configuration
@Import({SupplierController.class,
        JsonDataSource.class,
        RandomGenerator.class,
        CommonConfig.class})
public class SupplierConfig {
}
