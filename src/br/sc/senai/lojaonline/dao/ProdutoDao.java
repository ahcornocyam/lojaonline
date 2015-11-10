package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.Produto;

@Stateless
public class ProdutoDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public Produto salvarProduto( Produto produto ) throws ValidacaoException{

		if( produto.getNome().length() < 3 ){
			throw new ValidacaoException( "O nome do produto não pode ser menor que 3" );
		}

		if ( produto.getId() == null ){
			eM.persist( produto );
		}else{
			produto = eM.merge( produto );
		}

		return produto;
	}

	public List<Produto> listarProdutos(){

		StringBuilder jpql = new StringBuilder();
		jpql.append( "SELECT p " );
		jpql.append( "FROM Produto p " );
		jpql.append( "ORDER BY p.nome ASC" );

		TypedQuery<Produto> tQuery = eM.createQuery( jpql.toString(), Produto.class );
		List<Produto> produtos = tQuery.getResultList();

		return produtos;

	}

	public void excluirProduto( Produto produto ){

		eM.remove( eM.getReference( Produto.class, produto.getId() ) );

	}

}
