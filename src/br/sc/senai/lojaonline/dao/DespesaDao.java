package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.sc.senai.lojaonline.model.Despesa;

@Stateless
public class DespesaDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public List<Despesa> listar(){

		StringBuilder jpql = new StringBuilder();
		jpql.append( "SELECT d " );
		jpql.append( "FROM Despesa d " );

		return eM.createQuery(jpql.toString(),Despesa.class).getResultList();
	}

	public Despesa salvar( Despesa despesa ){


		if( despesa.getId() == null ){

			eM.persist( despesa );

		}else{

			eM.merge( despesa );
		}

		return despesa;
	}

	public void excluir( Despesa despesa ){

		eM.remove( eM.getReference( Despesa.class, despesa.getId() ) );

	}
}
