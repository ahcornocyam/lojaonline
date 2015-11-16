package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.TipoDespesa;

@Stateless
public class TipoDespesaDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public List<TipoDespesa> listar(){

		StringBuilder jpql =  new StringBuilder();
		jpql.append( "SELECT td " );
		jpql.append( "FROM TipoDespesa td " );
		jpql.append( "ORDER BY td.nome ASC" );

		return eM.createQuery( jpql.toString(),TipoDespesa.class ).getResultList();


	}

	public TipoDespesa salvar( TipoDespesa tipoDespesa) throws ValidacaoException{

		if ( tipoDespesa.getNome().length() < 3 ){

			throw new ValidacaoException("Precisa ser maior que 3 o nome do tipo de despesa" );
		}

		if ( tipoDespesa.getId() == null  ){

			eM.persist( tipoDespesa );
		}else{
			eM.merge( tipoDespesa );
		}

		return tipoDespesa;
	}

	public void excluir( TipoDespesa tipoDespesa ){

		eM.remove( eM.getReference( TipoDespesa.class, tipoDespesa.getId() ) );

	}
}
