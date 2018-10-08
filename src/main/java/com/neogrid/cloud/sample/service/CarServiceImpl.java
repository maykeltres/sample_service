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
package com.neogrid.cloud.sample.service;

import com.neogrid.cloud.sample.exception.CarNotFoundException;
import com.neogrid.cloud.sample.model.Car;
import com.neogrid.cloud.sample.params.CarSearchParams;
import com.neogrid.cloud.sample.repository.CarRepository;
import com.neogrid.cloud.sample.repository.predicates.CarPredicates;
import com.neogrid.cloud.sample.resource.CarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of Car Service
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    /**
     * @see CarService#create(CarResource)
     */
    @Override
    @Transactional
    public CarResource create(CarResource carResource) {
        return createOrUpdate(carResource);
    }

    /**
     * @param carId
     * @see CarService#read(UUID)
     */
    @Override
    @Transactional(readOnly = true)
    public CarResource read(UUID carId) {
        checkCarExists(carId);
        return mapCarToCarResource.apply(carRepository.findOne(carId));
    }

    /**
     * @see CarService#update(CarResource)
     */
    @Override
    @Transactional
    public void update(CarResource carResource) {
        checkCarExists(carResource.getId());
        createOrUpdate(carResource);
    }

    /**
     * @param carId
     * @see CarService#delete(UUID)
     */
    @Override
    @Transactional
    public void delete(UUID carId) {
        checkCarExists(carId);
        carRepository.delete(carId);
    }

    /**
     * @see CarService#search(CarSearchParams)
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CarResource> search(CarSearchParams carSearchParams) {
        Page<Car> page = carRepository.findAll(new CarPredicates(carSearchParams).getPredicates(), carSearchParams.getPageable());
        List<CarResource> carResources = page.getContent().stream()
                .map(entity -> mapCarToCarResource.apply(entity))
                .collect(Collectors.toList());
        return new PageImpl<>(carResources, carSearchParams.getPageable(), page.getTotalElements());
    }

    /**
     * Check if the Car with id provided exist in the database
     *
     * @param idCar
     */
    private void checkCarExists(UUID idCar) {
        Boolean exists = carRepository.exists(idCar);
        if (!exists) {
            throw new CarNotFoundException();
        }
    }

    /**
     * Create Or Update a Car
     *
     * @param carResource
     * @return
     */
    private CarResource createOrUpdate(CarResource carResource) {
        Car car = mapCarResourceToCar.apply(carResource);
        car = carRepository.save(car);
        return mapCarToCarResource.apply(car);
    }

    /**
     * Convert Entity to Resource
     */
    private Function<Car, CarResource> mapCarToCarResource = car ->
            CarResource.builder()
                    .id(car.getId())
                    .assembler(car.getAssembler())
                    .model(car.getModel())
                    .modelYear(car.getModelYear())
                    .manufacturingYear(car.getManufacturingYear())
                    .build();

    /**
     * Convert Resource to Entity
     */
    private Function<CarResource, Car> mapCarResourceToCar = carResource ->
            Car.builder()
                    .id(carResource.getId())
                    .assembler(carResource.getAssembler())
                    .model(carResource.getModel())
                    .modelYear(carResource.getModelYear())
                    .manufacturingYear(carResource.getManufacturingYear())
                    .build();


}