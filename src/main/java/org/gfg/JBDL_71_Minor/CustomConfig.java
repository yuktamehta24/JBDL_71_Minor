package org.gfg.JBDL_71_Minor;

import org.gfg.JBDL_71_Minor.repository.ABCD;
import org.gfg.JBDL_71_Minor.repository.CustomBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    public CustomBookRepository customBookRepository() {
        return new ABCD();
    }
}
