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
package com.neogrid.cloud.sample.repository.predicates;

import com.neogrid.cloud.sample.model.QCar;
import com.neogrid.cloud.sample.params.CarSearchParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * QueryDSLPredicate of Car
 */
@Validated
public class CarPredicates {

    private final QCar car = QCar.car;
    private final CarSearchParams carSearchParams;
    private final BooleanBuilder builder = new BooleanBuilder();

    public CarPredicates(@NotNull CarSearchParams carSearchParams) {
        this.carSearchParams = carSearchParams;
    }

    /**
     * Return Car Predicates
     *
     * @return
     */
    public Predicate getPredicates() {
        appendModel(carSearchParams.getModel());
        return builder;
    }

    /**
     * Add Predicate car model
     *
     * @param name
     */
    private void appendModel(String name) {
        if (StringUtils.isNotBlank(name)) {
            builder.and(car.model.likeIgnoreCase(Expressions.asString("%").concat(name).concat("%")));
        }
    }

}

