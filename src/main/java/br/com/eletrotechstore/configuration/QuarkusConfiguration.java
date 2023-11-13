package br.com.eletrotechstore.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class QuarkusConfiguration {

  @Produces
  @ApplicationScoped
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
