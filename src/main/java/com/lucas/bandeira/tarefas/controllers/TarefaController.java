package com.lucas.bandeira.tarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lucas.bandeira.tarefas.dto.TarefaDto;

@Controller
public class TarefaController {
    @GetMapping("/tarefa")
    public ModelAndView cadastrarTarefa(){
        ModelAndView mv = new ModelAndView("CadastroTarefa");
        mv.addObject("tarefa", new TarefaDto());
        return mv;
    
    }
    @GetMapping("/index")
    public ModelAndView todasTarefas(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
