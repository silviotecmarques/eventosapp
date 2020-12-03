package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.EventoModel;
import com.eventosapp.repositories.ConvidadoRepository;
import com.eventosapp.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController<result> {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;

    @RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value="/editarEvento/{codigo}", method=RequestMethod.GET)
    public ModelAndView editarEvento(@PathVariable("codigo") String codigo) {

        EventoModel evento = er.findById(Long.parseLong(codigo)).get();

        ModelAndView mv = new ModelAndView("evento/formEvento");

        mv.addObject("nome", evento.getNome());
        mv.addObject("local", evento.getLocal());
        mv.addObject("data", evento.getData());
        mv.addObject("horario", evento.getHorario());

        return mv;
    }

    @RequestMapping(value = "/cadastrarEvento", method=RequestMethod.POST)
    public String form(@Valid EventoModel evento, BindingResult result, RedirectAttributes attributes){
       if (result.hasErrors()){
           attributes.addFlashAttribute("mensagem", "Verifique os Campos!");
           return "redirect:/cadastrar/Evento";
       }
        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Eventos Cadastrados Com Sucesso!");
        return "redirect:/cadastrar/Evento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<EventoModel> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method=RequestMethod. GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        EventoModel evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);

        return mv;
    }

    @RequestMapping(value = "/{codigo}", method=RequestMethod. POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes)  {
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique Os Campos!");
            return "redirect:/{codigo}";
        }
        EventoModel evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado Adicionado Com Sucesso!");
        return "redirect:/{codigo}";
    }
}

