package org.urlshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.urlshortener.dto.ShortUrlRequest;
import org.urlshortener.dto.ShortUrlResponse;
import org.urlshortener.services.UrlShortenerService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/url")
public class UrlController {

    private final UrlShortenerService urlShortenerService;


    @PostMapping("/create-shorten-url")
    public ResponseEntity<ShortUrlResponse> createUrl(@RequestBody ShortUrlRequest request) {
        ShortUrlResponse response = urlShortenerService.createShortenUrl(request);

//        return redirect(response.getKey());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{key}")
    public RedirectView redirect(@PathVariable String key) {
        return urlShortenerService.getFullUrl(key);
    }
}
