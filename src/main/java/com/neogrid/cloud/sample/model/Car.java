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
package com.neogrid.cloud.sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * Entity that represent a Car in the domain model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR")
public class Car implements Serializable {
    
    private static final long serialVersionUID = 3641799834206984721L;

    @Id
    @GeneratedValue
    // Can be used with BINARY(16) in the database
    @Type(type = "uuid-char")
    @Column(name = "id_car", nullable = false, length = 36)
    private UUID id;
    
    @Size(min = 1, max = 64)
    @NotEmpty
    @Column(name = "nm_assembler", nullable = false, length = 64)
    private String assembler;
    
    @Size(min = 1, max = 64)
    @NotEmpty
    @Column(name = "nm_model", nullable = false, length = 64)
    private String model;
    
    @NotNull
    @Column(name = "nr_manufacturing_year", nullable = false)
    private Integer manufacturingYear;
    
    @NotNull
    @Column(name = "nr_model_year", nullable = false)
    private Integer modelYear;

}
