package org.bootcamp.trashhunter.controllers;

import org.bootcamp.trashhunter.models.dto.OfferDto;
import org.bootcamp.trashhunter.services.abstraction.OfferService;
import org.bootcamp.trashhunter.services.impl.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestMapController {

    @Autowired
    private final OfferService offerService;

    public RestMapController(OfferServiceImpl offerServiceImpl) {
        this.offerService = offerServiceImpl;
    }

    //todo (will be done after Matvey merge)
    @GetMapping("/offer/coordinates")
    public List<OfferDto> geoCoordinates() {
        return offerService.getAll().stream().map(OfferDto::getDtoIdCoordTrash).collect(Collectors.toList());
    }

    @GetMapping("/offer/{id}")
    public OfferDto getOfferById(@PathVariable("id") long id) {
        return OfferDto.getDto(offerService.getById(id));
    }


}
