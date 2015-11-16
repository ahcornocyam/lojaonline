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

import br.sc.senai.lojaonline.dao.TipoDespesaDao;
import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.TipoDespesa;

@Named( value = "tipoDespesaBean" )
@SessionScoped
public class TipoDespesaBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private TipoDespesa tipoDespesa;
	private List<TipoDespesa> tipoDespesas;

	@Inject
	private TipoDespesaDao tipoDespesaDao;

	@PostConstruct
	public void inicalizar(){

		tipoDespesa = new TipoDespesa();
		tipoDespesas = tipoDespesaDao.listar();
	}

	public void salvar(){

		try {
			tipoDespesaDao.salvar( tipoDespesa );
			tipoDespesas = tipoDespesaDao.listar();

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,tipoDespesa.getNome(),"Tipo de despesa salva!" );
			FacesContext.getCurrentInstance().addMessage( null, msg );

			tipoDespesa = new TipoDespesa();

		} catch ( ValidacaoException e ) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
	}

	public void editar( TipoDespesa tipoDespesa ){
		this.tipoDespesa = tipoDespesa;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroTipoDespesa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover( TipoDespesa tipoDespesa ){
		tipoDespesaDao.excluir( tipoDespesa );
		tipoDespesas = tipoDespesaDao.listar();

		FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,tipoDespesa.getNome(),"Tipo de despesa removida." );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}


	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa( TipoDespesa tipoDespesa ) {
		this.tipoDespesa = tipoDespesa;
	}

	public List<TipoDespesa> getTipoDespesas() {
		return tipoDespesas;
	}
	public void setTipoDespesas( List<TipoDespesa> tipoDespesas ) {
		this.tipoDespesas = tipoDespesas;
	}
}
