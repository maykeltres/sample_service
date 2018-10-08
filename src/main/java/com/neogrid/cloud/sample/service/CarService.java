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

import com.neogrid.cloud.sample.params.CarSearchParams;
import com.neogrid.cloud.sample.resource.CarResource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Service of Car
 */
@Validated
public interface CarService {

    /**
     * Create a car
     *
     * @param carResource
     */
    CarResource create(@NotNull @Valid CarResource carResource);

    /**
     * Get a car by Id
     *
     * @param carId
     * @return
     */
    CarResource read(@NotNull UUID carId);

    /**
     * Update a car by Id
     *
     * @param carResource
     */
    void update(@NotNull @Valid CarResource carResource);

    /**
     * Delete a car by Id
     *
     * @param carId
     */
    void delete(@NotNull UUID carId);

    /**
     * Search the cars based on car parameters
     *
     * @param carSearchParams
     * @return
     */
    Page<CarResource> search(@NotNull CarSearchParams carSearchParams);

}