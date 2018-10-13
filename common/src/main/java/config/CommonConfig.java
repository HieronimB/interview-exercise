package config;

import json.JsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JsonParser.class)
public class CommonConfig {
}
