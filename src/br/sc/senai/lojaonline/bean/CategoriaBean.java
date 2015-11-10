package br.sc.senai.lojaonline.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.sc.senai.lojaonline.dao.CategoriaDAO;
import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.Categoria;

@Named(value="categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private List<Categoria> categorias;


	@Inject
	private CategoriaDAO categoriaDAO;

	public void inserir(){
		categoriaDAO.insereCategoria();
	}

	@PostConstruct
	public void inicializar(){

		setCategoria( new Categoria() );
		categorias = categoriaDAO.listarCategorias();

	}

	public void salvarCategoria(){

		try {
			categoriaDAO.salvarCategoria(categoria);
			categorias = categoriaDAO.listarCategorias();

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,categoria.getNome(),"Categoria salva com sucesso!" );
			FacesContext.getCurrentInstance().addMessage( null, msg );

			setCategoria( new Categoria() );

		} catch ( ValidacaoException e ) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
	}


	public void editarProduto( Categoria categoria ){
		this.categoria = categoria;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroCategoria.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removerCategoria( Categoria categoria ){
		categoriaDAO.excluirCategoria( categoria );
		categorias = categoriaDAO.listarCategorias();

		FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,categoria.getNome(),"Categoria removida." );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
