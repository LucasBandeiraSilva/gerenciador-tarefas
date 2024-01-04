package com.lucas.bandeira.tarefas.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTarefa {
    PENDENTE("Pendente"), REALIZADA("Realizada"),
    EM_ANDAMENTO("Em andamento");

    private String status;
}
