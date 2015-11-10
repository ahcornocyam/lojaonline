package br.sc.senai.lojaonline.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.sc.senai.lojaonline.dao.CategoriaDAO;
import br.sc.senai.lojaonline.model.Categoria;

@Named(value="categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	public void inserir(){
		categoriaDAO.insereCategoria();
	}
	
	private List<Categoria> categorias;
	
	
	@PostConstruct
	public void inicializar(){
		
		categorias = new ArrayList<>();
		
		categorias = categoriaDAO.listarCategorias();
		
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

}
