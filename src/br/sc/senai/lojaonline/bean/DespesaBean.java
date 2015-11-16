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

import br.sc.senai.lojaonline.dao.DespesaDao;
import br.sc.senai.lojaonline.model.Despesa;

@Named( value = "despesaBean" )
@SessionScoped
public class DespesaBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Despesa despesa;

	private List<Despesa> despesas;

	@Inject
	private TipoDespesaBean tipoDespesas;

	@Inject
	private DespesaDao despesaDao;



	@PostConstruct
	public void inicializar(){

		despesa = new Despesa();
		despesas = despesaDao.listar();
	}

	public void Salvar(){

		try {

			despesaDao.salvar( despesa );
			despesas = despesaDao.listar();


			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Despesa","Despesa salva com sucesso!" );
			FacesContext.getCurrentInstance().addMessage( null, msg );

			despesa = new Despesa();

		} catch ( Exception e ) {
			// TODO: handle exception

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}

	}

	public void editar( Despesa despesa ){

		this.despesa = despesa;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroDespesa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover( Despesa despesa ){

		this.despesaDao.excluir( despesa );

		despesas = despesaDao.listar();

		FacesMessage msg  = new FacesMessage( FacesMessage.SEVERITY_INFO,"Despesa","Despesa removida!" );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}


	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa( Despesa despesa ) {
		this.despesa = despesa;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas( List<Despesa> despesas ) {
		this.despesas = despesas;
	}

	public TipoDespesaBean getTipoDespesas() {
		return tipoDespesas;
	}

	public void setTipoDespesas(TipoDespesaBean tipoDespesas) {
		this.tipoDespesas = tipoDespesas;
	}
}
