package com.pasakinskas.homework.config;
import com.pasakinskas.homework.HelloWorld;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() {
        register(HelloWorld.class);
    }
}