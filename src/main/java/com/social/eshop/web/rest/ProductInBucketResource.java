package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.service.ProductInBucketService;
import com.social.eshop.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing ProductInBucket.
 */
@RestController
@RequestMapping("/api")
public class ProductInBucketResource {

    private final Logger log = LoggerFactory.getLogger(ProductInBucketResource.class);

    private static final String ENTITY_NAME = "productInBucket";

    private final ProductInBucketService productInBucketService;

    public ProductInBucketResource(ProductInBucketService productInBucketService) {
        this.productInBucketService = productInBucketService;
    }

    /**
     * POST  /product-in-buckets : Create a new productInBucket.
     *
     * @param productInBucket the productInBucket to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productInBucket, or with status 400 (Bad Request) if the productInBucket has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-in-buckets")
    @Timed
    public ResponseEntity<ProductInBucket> createProductInBucket(@RequestBody ProductInBucket productInBucket) throws URISyntaxException {
        log.debug("REST request to save ProductInBucket : {}", productInBucket);
        if (productInBucket.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new productInBucket cannot already have an ID")).body(null);
        }
        ProductInBucket result = productInBucketService.save(productInBucket);
        return ResponseEntity.created(new URI("/api/product-in-buckets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-in-buckets : Updates an existing productInBucket.
     *
     * @param productInBucket the productInBucket to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productInBucket,
     * or with status 400 (Bad Request) if the productInBucket is not valid,
     * or with status 500 (Internal Server Error) if the productInBucket couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-in-buckets")
    @Timed
    public ResponseEntity<ProductInBucket> updateProductInBucket(@RequestBody ProductInBucket productInBucket) throws URISyntaxException {
        log.debug("REST request to update ProductInBucket : {}", productInBucket);
        if (productInBucket.getId() == null) {
            return createProductInBucket(productInBucket);
        }
        ProductInBucket result = productInBucketService.save(productInBucket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productInBucket.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-in-buckets : get all the productInBuckets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productInBuckets in body
     */
    @GetMapping("/product-in-buckets")
    @Timed
    public List<ProductInBucket> getAllProductInBuckets() {
        log.debug("REST request to get all ProductInBuckets");
        return productInBucketService.findAll();
    }

    /**
     * GET  /product-in-buckets/:id : get the "id" productInBucket.
     *
     * @param id the id of the productInBucket to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productInBucket, or with status 404 (Not Found)
     */
    @GetMapping("/product-in-buckets/{id}")
    @Timed
    public ResponseEntity<ProductInBucket> getProductInBucket(@PathVariable Long id) {
        log.debug("REST request to get ProductInBucket : {}", id);
        ProductInBucket productInBucket = productInBucketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productInBucket));
    }

    /**
     * DELETE  /product-in-buckets/:id : delete the "id" productInBucket.
     *
     * @param id the id of the productInBucket to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-in-buckets/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductInBucket(@PathVariable Long id) {
        log.debug("REST request to delete ProductInBucket : {}", id);
        productInBucketService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/product-in-buckets?query=:query : search for the productInBucket corresponding
     * to the query.
     *
     * @param query the query of the productInBucket search
     * @return the result of the search
     */
    @GetMapping("/_search/product-in-buckets")
    @Timed
    public List<ProductInBucket> searchProductInBuckets(@RequestParam String query) {
        log.debug("REST request to search ProductInBuckets for query {}", query);
        return productInBucketService.search(query);
    }

}
