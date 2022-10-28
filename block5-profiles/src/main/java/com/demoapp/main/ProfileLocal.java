package com.demoapp.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile(value="local")
class ProfileLocal implements CommandLineRunner {
    @Value("${message:error al cargar propiedades en local}")
    String activeProfile;

    @Override
    public void run(String... args) throws Exception {
        log.info("Properties Status: " + activeProfile);
    }
}