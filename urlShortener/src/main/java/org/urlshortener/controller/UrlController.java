package org.urlshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.urlshortener.dto.ShortUrlRequest;
import org.urlshortener.dto.ShortUrlResponse;
import org.urlshortener.services.UrlShortenerService;


@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/url")
public class UrlController {

    private final UrlShortenerService urlShortenerService;


    @PostMapping("/api/v1/url/create-shorten-url")
    public ResponseEntity<ShortUrlResponse> createUrl(@RequestBody ShortUrlRequest request) {
        ShortUrlResponse response = urlShortenerService.createShortenUrl(request);

//        return redirect(response.getKey());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/shorturl/{url}")
    public RedirectView redirect(@PathVariable String url) {
        return urlShortenerService.getFullUrl(url);
    }
}
