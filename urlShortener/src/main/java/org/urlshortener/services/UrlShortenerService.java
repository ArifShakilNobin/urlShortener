package org.urlshortener.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import org.urlshortener.dto.ShortUrlRequest;
import org.urlshortener.dto.ShortUrlResponse;
import org.urlshortener.entity.ShortUrlEntity;
import org.urlshortener.repository.ShortUrlRepository;
import org.urlshortener.util.ShortUrlUtil;



@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final ShortUrlRepository urlRepository;
    private final ShortUrlUtil shortUrlUtil;
    private final String BASE_URL="http://localhost:1234/api/v1/url";

    @Value("${dynamic.key.upper}")
    private String DYNAMIC_VALUE;

    private final String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public ShortUrlResponse createShortenUrl(ShortUrlRequest request) {
        String fullUrl = request.getUrl();

        ShortUrlEntity existingShortUrl = urlRepository.findByFullUrl(fullUrl);

        if (existingShortUrl != null) {
            return ShortUrlResponse.builder().key(existingShortUrl.getKey()).build();
        } else {
            String newKey = shortUrlUtil.generateUniqueKey();

            String shortUrl = BASE_URL + "/" + newKey;

//            String replacedUrl = BASE_URL.replaceFirst("/$", "/" + DYNAMIC_VALUE + "/");
//            System.out.println("--------------"+replacedUrl);

            ShortUrlEntity shortUrlEntity = ShortUrlEntity.builder()
                    .key(newKey)
                    .fullUrl(fullUrl)
                    .shortUrl(shortUrl )
                    .clickCount(0L)
                    .build();

            urlRepository.save(shortUrlEntity);
            return ShortUrlResponse.builder().key(shortUrl).build();
        }
    }

    public RedirectView getFullUrl(String key) {

        ShortUrlEntity shortUrlEntity = urlRepository.findByKey(key);
        if (shortUrlEntity == null) {
            return new RedirectView("/");
        }
        shortUrlEntity.setClickCount(shortUrlEntity.getClickCount() + 1);
        urlRepository.save(shortUrlEntity);
        return new RedirectView(shortUrlEntity.getFullUrl());
    }




}
