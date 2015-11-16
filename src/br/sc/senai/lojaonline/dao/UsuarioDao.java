package br.sc.senai.lojaonline.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.sc.senai.lojaonline.model.Usuario;

@Stateless
public class UsuarioDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public Boolean autenticarUsuario( Usuario usuario ){

		StringBuilder jpql =  new StringBuilder();
		jpql.append( "SELECT u " );
		jpql.append( "FROM Usuario u " );
		jpql.append( "WHERE u.login = :login " );
		jpql.append( "AND u.senha = :senha " );

		TypedQuery<Usuario> tQuery = eM.createQuery( jpql.toString() ,Usuario.class );
		tQuery.setParameter( "login", usuario.getLogin() );
		tQuery.setParameter( "senha", usuario.getSenha() );

		Usuario autenticado = null;

		try {
			autenticado = tQuery.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}

		boolean tolken;

		if ( autenticado != null ){
			tolken = true;
		}else{
			tolken = false;
		}

		return tolken;
	}


}
