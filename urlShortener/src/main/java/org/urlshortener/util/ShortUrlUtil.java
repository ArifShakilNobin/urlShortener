package org.urlshortener.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshortener.config.ShortUrlConfig;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ShortUrlUtil {

    private final ShortUrlConfig shortUrlConfig;

    public String generateUniqueKey() {
        int keyLength = shortUrlConfig.getKeyLength();
        String allowedChars = shortUrlConfig.getAllowedCharacters();

        StringBuilder keyBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < keyLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            keyBuilder.append(allowedChars.charAt(randomIndex));
        }

        return keyBuilder.toString();
    }
}
