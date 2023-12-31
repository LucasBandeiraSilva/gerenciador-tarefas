package com.lucas.bandeira.tarefas.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTarefa {
    PENDENTE("Pendente"), REALIZADA("Realizada"),
    CONCLUIDA("Concluida"), EM_ANDAMENTO("Em_andamento");

    private String status;
}
