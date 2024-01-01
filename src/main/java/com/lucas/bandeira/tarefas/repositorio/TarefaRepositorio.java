package com.lucas.bandeira.tarefas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.bandeira.tarefas.entidade.Tarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa,Long> {
    
}
