/*
 * Copyright (c) 2018, NeoGrid. Todos os direitos reservados.
 *
 * Os Programas desta aplicação (que incluem tanto o software quanto a sua
 * documentação) contém informações proprietárias da NeoGrid; eles são
 * licenciados de acordo com um contrato de licença contendo restrições de uso e
 * confidencialidade, e são também protegidos pela Lei 9609/98 e 9610/98,
 * respectivamente Lei do Software e Lei dos Direitos Autorais. Engenharia
 * reversa, descompilação e desmontagem dos programas são proibidos. Nenhuma
 * parte destes programas pode ser reproduzida ou transmitida de nenhuma forma e
 * por nenhum meio, eletrônico ou mecânico, por motivo algum, sem a permissão
 * escrita da NeoGrid.
 *
 * Copyright (c) 2018, NeoGrid. All rights reserved.
 *
 * The contents of this software are proprietary and confidential to
 * the NeoGrid Company. Use of this information is to be in
 * accordance with the terms of the license agreement you entered
 * into with NeoGrid. Individuals having access to this
 * software are responsible for maintaining the confidentiality of
 * the content and for keeping the software secure when not in use.
 * Transfer to any party is strictly forbidden other than as expressly
 * permitted in writing by NeoGrid Company. Unauthorized
 * transfer to or possession by any unauthorized party may be a
 * criminal offense.
 */
package com.neogrid.cloud.sample.controller;

import com.neogrid.cloud.sample.params.CarSearchParams;
import com.neogrid.cloud.sample.resource.CarResource;
import com.neogrid.cloud.sample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

/**
 * REST API that contains resources regarding to Car
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(value = {"", "/"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createCar(@RequestBody @Valid CarResource carResource) {
        carResource = carService.create(carResource);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carResource.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{carId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> readCar(@PathVariable(value = "carId") UUID carId) {
        return ResponseEntity.ok(carService.read(carId));
    }

    @PutMapping(value = "/{carId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateCar(@PathVariable(value = "carId") UUID carId, @RequestBody @Valid CarResource carResource) {
        carResource.setId(carId);
        carService.update(carResource);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable(value = "carId") UUID carId) {
        carService.delete(carId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = {"/search"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<CarResource>> searchCars(CarSearchParams carSearchParams, Pageable pageable) {
        carSearchParams.setPageable(pageable);
        return ResponseEntity.ok(carService.search(carSearchParams));
    }

}