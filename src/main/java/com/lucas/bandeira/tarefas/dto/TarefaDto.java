package com.lucas.bandeira.tarefas.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.lucas.bandeira.tarefas.entidade.StatusTarefa;
import com.lucas.bandeira.tarefas.entidade.Tarefa;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaDto {
    @NotBlank(message = "Você precisa fornecer um titulo para tarefa")
    @Length(max = 35, message = "Você precisa fornecer um título menor")
    private String titulo;
    @NotBlank(message = "Você precisa fornecer uma descrição para tarefa")
    @Length(max = 100,message = "A descrição é muito longa")
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;
    @NotNull(message="Data de vencimento não pode ser nula")
    @Future(message = "Você precisa fornecer uma data futura")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date dataTermino;

    public Tarefa tarefaConverter(){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(this.titulo);
        tarefa.setDescricao(this.descricao);
        tarefa.setDataTermino(this.dataTermino);
        return tarefa;
    }

    public Tarefa toTarefa(Tarefa tarefa){
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setStatusTarefa(statusTarefa);
        tarefa.setDataTermino(dataTermino);
        return tarefa;
    }
}
