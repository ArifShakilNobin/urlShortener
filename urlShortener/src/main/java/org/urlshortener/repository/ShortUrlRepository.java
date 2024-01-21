package org.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.urlshortener.entity.ShortUrlEntity;
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {
    ShortUrlEntity findByKey(String key);
    ShortUrlEntity findByFullUrl(String fullUrl);

    ShortUrlEntity findByShortUrl(String shortUrl);

}
