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

import br.sc.senai.lojaonline.dao.TipoReceitaDao;
import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.TipoReceita;

@Named( value = "tipoReceitaBean" )
@SessionScoped
public class TipoReceitaBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private TipoReceita tipoReceita;
	private List<TipoReceita> tipoReceitas;


	@Inject
	private TipoReceitaDao tipoReceitaDao;

	@PostConstruct
	public void inicalizar(){

		tipoReceita = new TipoReceita();
		tipoReceitas = tipoReceitaDao.listar();
	}

	public void salvar(){

		try {
			tipoReceitaDao.salvar( tipoReceita );
			tipoReceitas = tipoReceitaDao.listar();

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,tipoReceita.getNome(),"Tipo de receita salva!" );
			FacesContext.getCurrentInstance().addMessage( null, msg );

			tipoReceita = new TipoReceita();

		} catch ( ValidacaoException e ) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
	}

	public void editar( TipoReceita tipoReceita ){
		this.tipoReceita = tipoReceita;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroTipoReceita.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover( TipoReceita tipoReceita ){
		tipoReceitaDao.excluir( tipoReceita );
		tipoReceitas = tipoReceitaDao.listar();

		FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,tipoReceita.getNome(),"Tipo de Receita removida." );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}


	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}
	public void setTipoReceita( TipoReceita tipoReceita ) {
		this.tipoReceita = tipoReceita;
	}

	public List<TipoReceita> getTipoReceitas() {
		return tipoReceitas;
	}
	public void setTipoReceitas( List<TipoReceita> tipoReceitas ) {
		this.tipoReceitas = tipoReceitas;
	}
}
