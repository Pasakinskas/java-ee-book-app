package com.pasakinskas.homework.config;
import com.pasakinskas.homework.controller.BooksController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() {
        register(BooksController.class);
    }
}