package com.lucas.bandeira.tarefas.entidade;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_tarefa")
@Getter
@Setter
public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDate dataTermino;
}
