package wanted.pre_onboarding.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(title = "채용공고 API",
                description = "프리온보딩 백엔드 인턴십 선발과제 API 문서"))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/wanted/**"};

        return GroupedOpenApi.builder()
                .group("채용공고 지원 API")
                .pathsToMatch(paths)
                .build();
    }
}