package br.sc.senai.lojaonline.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.sc.senai.lojaonline.dao.UsuarioDao;
import br.sc.senai.lojaonline.model.Usuario;
@Named( value = "usuarioBean" )
@SessionScoped
public class UsuarioBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private Usuario usuario;

	@Inject
	private UsuarioDao usuarioDao;

	@PostConstruct
	public void inicializar(){
		usuario = new Usuario();
	}

	public void login(){

		boolean autenticado = usuarioDao.autenticarUsuario( usuario );

		if ( autenticado == true ){
			try {
					this.redirect( "/LojaOnline/sessao.xhtml" );

			} catch (Exception e) {
				// TODO: handle exception
				FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage() );
				FacesContext.getCurrentInstance().addMessage( null, msg );
			}
		}else if ( autenticado == false ){
			this.redirect( "/LojaOnline/index.xhtml" );
		}
	}

	public void redirect( String page ){
		try {
			FacesContext.
						getCurrentInstance().
						getExternalContext().
						redirect( page );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
