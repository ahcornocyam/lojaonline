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

import br.sc.senai.lojaonline.dao.ReceitaDao;
import br.sc.senai.lojaonline.model.Receita;

@Named( value = "receitaBean" )
@SessionScoped
public class ReceitaBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Receita receita;

	private List<Receita> receitas;

	@Inject
	private TipoReceitaBean tipoReceitas;

	@Inject
	private ReceitaDao receitaDao;



	@PostConstruct
	public void inicializar(){

		this.receita = new Receita();
		this.receitas =  receitaDao.listar();
	}

	public void Salvar(){

		try {

			receitaDao.salvar( receita );
			setReceitas( receitaDao.listar() );

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Receita","Receita salva com sucesso!" );
			FacesContext.getCurrentInstance().addMessage( null, msg );

			setReceita( new Receita() );

		} catch ( Exception e ) {
			// TODO: handle exception

			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}

	}

	public void editar( Receita receita ){

		this.receita = receita;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroReceita.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover( Receita receita ){

		this.receitaDao.excluir( receita );

		setReceitas( receitaDao.listar() );

		FacesMessage msg  = new FacesMessage( FacesMessage.SEVERITY_INFO,"Receita","Receita removida!" );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}


	public Receita getReceita() {
		return receita;
	}

	public void setReceita( Receita receita ) {
		this.receita = receita;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas( List<Receita> receitas ) {
		this.receitas = receitas;
	}

	public TipoReceitaBean getTipoReceitas() {
		return tipoReceitas;
	}

	public void setTipoReceitas(TipoReceitaBean tipoReceitas) {
		this.tipoReceitas = tipoReceitas;
	}
}
