package com.desafio.accenture.qa.support;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public final class ResourceFiles {

    private ResourceFiles() {
    }

    public static String absolutePath(String classpathLocation) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(classpathLocation);
        if (resource != null) {
            try {
                return new File(resource.toURI()).getAbsolutePath();
            } catch (URISyntaxException exception) {
                throw new IllegalStateException("Nao foi possivel resolver o recurso: " + classpathLocation, exception);
            }
        }
        return Paths.get("src", "test", "resources", classpathLocation).toAbsolutePath().toString();
    }
}
