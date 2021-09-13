package me.greeenly.catalogservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.greeenly.catalogservice.domain.Catalog;
import me.greeenly.catalogservice.repository.CatalogRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
