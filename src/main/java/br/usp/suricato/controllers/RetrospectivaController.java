package br.usp.suricato.controllers;

import java.security.Principal;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.daos.LousaDao;
import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Retrospectiva;
import br.usp.suricato.models.Time;
import br.usp.suricato.models.Usuario;

@Controller
@Transactional
@RequestMapping("/retrospectiva")
public class RetrospectivaController {

	@Autowired
	private RetrospectivaDao retrospectivaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private LousaDao lousaDao;

	@RequestMapping("/mostra")
	public String mostraRetrospectiva(Integer id, Model model, Principal principal) {
		Retrospectiva retrospectiva = retrospectivaDao.load(id);
		if(!retrospectiva.isPublica()) {
			Usuario usuario = (principal == null) ? new Usuario() : usuarioDao.buscaPorNome(principal.getName());
			if(!retrospectiva.isUsuarioAutorizado(usuario) && !retrospectiva.isPublica()) {
				return "redirect:/index";
			}
		}
		model.addAttribute("retrospectiva", retrospectiva);
		return "retrospectiva/mostra";
	}
	
	@RequestMapping("/cria")
	public String criaRestrospectiva(Retrospectiva retrospectiva, Model model) {
		if(retrospectiva.getTime().getId() == null) {
			retrospectiva.setTime(null);
		}
		retrospectiva.setCriador(usuarioDao.buscaPorNome(retrospectiva.getCriador().getNome()));
		retrospectiva.setLousa(lousaDao.load(retrospectiva.getLousa().getId()));
		retrospectivaDao.salva(retrospectiva);
		return "redirect:mostra?id=" + retrospectiva.getId();
	}
	
	@RequestMapping("/nova")
	public String novaRetrospectiva(Model model, Principal principal) {
		model.addAttribute("lousas", lousaDao.lista());
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		model.addAttribute("hoje", Calendar.getInstance());
		return "retrospectiva/nova";
	}

	@RequestMapping("usuario/lista")
	public String listaRetrospectivasDoUsuario(Usuario usuario, Model model) {
		model.addAttribute("retrospectivasAbertas", retrospectivaDao.listaRetrospectivasAbertasDoUsuario(usuario.getId()));
		model.addAttribute("retrospectivasFechadas", retrospectivaDao.listaRetrospectivasEncerradasDoUsuario(usuario.getId()));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("time/lista")
	public String listaRetrospectivasDoTime(Time time, Model model) {
		model.addAttribute("retrospectivasAbertas", retrospectivaDao.listaRetrospectivasAbertasDoTime(time.getId()));
		model.addAttribute("retrospectivasFechadas", retrospectivaDao.listaRetrospectivasEncerradasDoTime(time.getId()));
		return "retrospectiva/lista";
	}
	
}
