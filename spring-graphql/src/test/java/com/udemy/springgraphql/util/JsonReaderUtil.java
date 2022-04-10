package com.udemy.springgraphql.util;

import org.apache.commons.io.IOUtils;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonReaderUtil {

    public static String read(String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(location).getInputStream(),
                StandardCharsets.UTF_8
        );
    }
}
