package org.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortUrlRequest {
    private String url;
}