package org.test.task.app.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SwaggerConfiguration {

    @Value("${domain.name}")
    private String domainName;

    @Value("${server.port}")
    private String port;

    /**
     * Base configurations for Swagger Ui, must include
     * domainName and port;
     *
     * @return Create OpenAPI configuration
     */
    @Bean
    public OpenAPI springShopOpenAPI() {

        ArrayList<Server> servers = new ArrayList<>();
        Server server = new Server();
        server.setUrl(domainName + ":" +port);
        servers.add(server);

        return new OpenAPI()
                .info(new Info().description("Синтаксический анализ строк")
                        .title("API")).servers(servers);
    }

    /**
     * Can be change controllers group name, and create target to scan
     *
     * @return Create GroupedOpenApi configuration
     */
    @Bean
    public GroupedOpenApi groupOpenApiV1() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"org.test.task.app.controller"};
        return GroupedOpenApi.builder().group("v1").pathsToMatch(paths).packagesToScan(packagesToScan).pathsToMatch("/","/**").build();
    }
}