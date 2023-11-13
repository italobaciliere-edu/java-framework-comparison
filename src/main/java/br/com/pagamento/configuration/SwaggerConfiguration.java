package br.com.pagamento.configuration;

import io.micronaut.context.annotation.Factory;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Factory
@OpenAPIDefinition(info = @Info(
    title = "Your API",
    version = "1.0",
    description = "API description"
))
public class SwaggerConfiguration { }
