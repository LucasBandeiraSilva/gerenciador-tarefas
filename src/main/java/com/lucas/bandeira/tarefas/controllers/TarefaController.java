package com.lucas.bandeira.tarefas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lucas.bandeira.tarefas.dto.TarefaDto;
import com.lucas.bandeira.tarefas.entidade.Tarefa;
import com.lucas.bandeira.tarefas.repositorio.TarefaRepositorio;

import jakarta.validation.Valid;

@Controller
public class TarefaController {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @GetMapping("/tarefa")
    public ModelAndView cadastrarTarefa() {
        ModelAndView mv = new ModelAndView("CadastroTarefa");
        mv.addObject("tarefa", new TarefaDto());
        return mv;

    }

    @PostMapping("salvar/tarefa")
    public ModelAndView salvarTarefa(@Valid @ModelAttribute("tarefa") TarefaDto tarefaDto, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("tem erros");
            mv.setViewName("CadastroTarefa");
        } else {
            Tarefa tarefa = tarefaDto.tarefaConverter();
            tarefaRepositorio.save(tarefa);
            System.out.println(tarefa.toString());
            mv.setViewName("redirect:/index");
        }
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView todasTarefas() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
