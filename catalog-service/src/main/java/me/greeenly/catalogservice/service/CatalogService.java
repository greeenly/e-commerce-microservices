package me.greeenly.catalogservice.service;

import me.greeenly.catalogservice.domain.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();

}
