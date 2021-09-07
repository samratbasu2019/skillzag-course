package com.org.skillzag.web.rest;

import com.org.skillzag.service.PromoService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.PromoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.skillzag.domain.Promo}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromoResource {

    private final Logger log = LoggerFactory.getLogger(PromoResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementPromo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromoService promoService;

    public PromoResource(PromoService promoService) {
        this.promoService = promoService;
    }

    /**
     * {@code POST  /promos} : Create a new promo.
     *
     * @param promoDTO the promoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promoDTO, or with status {@code 400 (Bad Request)} if the promo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promos")
    public ResponseEntity<PromoDTO> createPromo(@RequestBody PromoDTO promoDTO) throws URISyntaxException {
        log.debug("REST request to save Promo : {}", promoDTO);
        if (promoDTO.getId() != null) {
            throw new BadRequestAlertException("A new promo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PromoDTO result = promoService.save(promoDTO);
        return ResponseEntity.created(new URI("/api/promos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /promos} : Updates an existing promo.
     *
     * @param promoDTO the promoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promoDTO,
     * or with status {@code 400 (Bad Request)} if the promoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promos")
    public ResponseEntity<PromoDTO> updatePromo(@RequestBody PromoDTO promoDTO) throws URISyntaxException {
        log.debug("REST request to update Promo : {}", promoDTO);
        if (promoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PromoDTO result = promoService.save(promoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, promoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /promos} : get all the promos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promos in body.
     */
    @GetMapping("/promos")
    public ResponseEntity<List<PromoDTO>> getAllPromos(Pageable pageable) {
        log.debug("REST request to get a page of Promos");
        Page<PromoDTO> page = promoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /promos/:id} : get the "id" promo.
     *
     * @param id the id of the promoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promos/{id}")
    public ResponseEntity<PromoDTO> getPromo(@PathVariable Long id) {
        log.debug("REST request to get Promo : {}", id);
        Optional<PromoDTO> promoDTO = promoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(promoDTO);
    }

    /**
     * {@code DELETE  /promos/:id} : delete the "id" promo.
     *
     * @param id the id of the promoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promos/{id}")
    public ResponseEntity<Void> deletePromo(@PathVariable Long id) {
        log.debug("REST request to delete Promo : {}", id);
        promoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
