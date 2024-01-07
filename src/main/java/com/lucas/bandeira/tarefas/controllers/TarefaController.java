package com.lucas.bandeira.tarefas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lucas.bandeira.tarefas.dto.TarefaDto;
import com.lucas.bandeira.tarefas.entidade.StatusTarefa;
import com.lucas.bandeira.tarefas.entidade.Tarefa;
import com.lucas.bandeira.tarefas.repositorio.TarefaRepositorio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @GetMapping("/nova")
    public ModelAndView cadastrarTarefa() {
        ModelAndView mv = new ModelAndView("CadastroTarefa");
        mv.addObject("tarefa", new TarefaDto());
        return mv;

    }

    @PostMapping("/salvar")
    public ModelAndView salvarTarefa(@Valid @ModelAttribute("tarefa") TarefaDto tarefaDto, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("tem erros");
            mv.setViewName("CadastroTarefa");
        } else {
            Tarefa tarefa = tarefaDto.tarefaConverter();
            tarefa.setStatusTarefa(StatusTarefa.EM_ANDAMENTO);
            tarefaRepositorio.save(tarefa);
            mv.setViewName("redirect:/tarefa/");
        }
        return mv;
    }

    @GetMapping("/")
    public ModelAndView todasTarefas() {
        ModelAndView mv = new ModelAndView("index");
        List<Tarefa> listaDeTarefas = tarefaRepositorio.findAll();
        mv.addObject("listaDeTarefas", listaDeTarefas);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Tarefa> optionalTarefa = tarefaRepositorio.findById(id);
        if (optionalTarefa.isPresent()) {
            mv.setViewName("edit");
            mv.addObject("status", StatusTarefa.values());
            mv.addObject("tarefa", optionalTarefa.get());
        } else {
            mv.setViewName("erro");
        }
        return mv;

    }

    @PostMapping("/{id}")
    public ModelAndView salvarEdicao(@Valid TarefaDto tarefaDto, @PathVariable Long id,
            BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();

        Optional<Tarefa> tarefaOptional = tarefaRepositorio.findById(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaDto.toTarefa(tarefaOptional.get());

            tarefaRepositorio.save(tarefa);
            mv.setViewName("redirect:/tarefa/");
        } else {
            mv.setViewName("erro");
        }
        return mv;
    }
    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Optional<Tarefa> tarefaOptional = tarefaRepositorio.findById(id);
        if (tarefaOptional.isPresent()) {
            tarefaRepositorio.deleteById(id);
            mv.setViewName("redirect:/tarefa/");
        }
        else{
            mv.setViewName("erro");
        }
        return mv;
    }

}
