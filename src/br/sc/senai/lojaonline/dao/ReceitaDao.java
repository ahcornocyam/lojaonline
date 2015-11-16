package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.sc.senai.lojaonline.model.Receita;

@Stateless
public class ReceitaDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public List<Receita> listar(){

		StringBuilder jpql = new StringBuilder();
		jpql.append( "SELECT r " );
		jpql.append( "FROM Receita r " );

		return eM.createQuery(jpql.toString(),Receita.class).getResultList();
	}

	public Receita salvar( Receita receita ){

		if( receita.getId() == null ){

			eM.persist( receita );

		}else{

			eM.merge( receita );
		}

		return receita;
	}

	public void excluir( Receita receita ){

		eM.remove( eM.getReference( Receita.class, receita.getId() ) );

	}

}
