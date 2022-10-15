package com.example.configuration.properties;


import org.springframework.core.env.PropertySource;

public class BootifulPropertySource extends PropertySource<String> {

    public BootifulPropertySource() {
        super("bootiful");
    }

    @Override
    public Object getProperty(String name) {

        if (name.equalsIgnoreCase("bootiful-message")) {
            return "Hello from " + BootifulPropertySource.class.getSimpleName() + "!";
        }

        return null;
    }
}
