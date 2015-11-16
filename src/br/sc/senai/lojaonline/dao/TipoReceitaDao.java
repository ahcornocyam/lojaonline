package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.TipoReceita;

@Stateless
public class TipoReceitaDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public List<TipoReceita> listar(){

		StringBuilder jpql =  new StringBuilder();
		jpql.append( "SELECT tr " );
		jpql.append( "FROM TipoReceita tr " );
		jpql.append( "ORDER BY tr.nome ASC" );

		return eM.createQuery( jpql.toString(),TipoReceita.class ).getResultList();


	}

	public TipoReceita salvar( TipoReceita tipoReceita) throws ValidacaoException{

		if ( tipoReceita.getNome().length() < 3 ){

			throw new ValidacaoException("Precisa ser maior que 3 o nome do tipo de receita" );
		}

		if ( tipoReceita.getId() == null  ){

			eM.persist( tipoReceita );
		}else{
			eM.merge( tipoReceita );
		}

		return tipoReceita;
	}

	public void excluir( TipoReceita tipoReceita ){

		eM.remove( eM.getReference( TipoReceita.class, tipoReceita.getId() ) );

	}

}
