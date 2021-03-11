package study.eatgo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.eatgo.domain.restaurant.model.FoodCategoryConverter;
import study.eatgo.domain.restaurant.model.RegionConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RegionConverter());
        registry.addConverter(new FoodCategoryConverter());
    }
}
