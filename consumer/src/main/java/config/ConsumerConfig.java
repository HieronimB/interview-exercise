package config;

import csv.CsvConverter;
import httpclient.SupplierClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rest.ConsumerController;
import service.ConsumerService;

@Configuration
@Import({CsvConverter.class,
        SupplierClient.class,
        ConsumerService.class,
        ConsumerController.class,
        CommonConfig.class})
public class ConsumerConfig {
}
