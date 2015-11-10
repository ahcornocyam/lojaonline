package br.sc.senai.lojaonline.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.Categoria;

@Stateless
public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager eM;

	public List<Categoria> listarCategorias(){
		String jpql = "select c from Categoria c";
		List<Categoria> categorias = eM.createQuery(jpql,Categoria.class)
				                                  .getResultList();

		return categorias;
	}

	public void insereCategoria(){

		Categoria categoria = new Categoria();
		categoria.setNome("Alimentos");

		eM.persist(categoria);

	}

	public Categoria salvarCategoria( Categoria categoria ) throws ValidacaoException{

		if ( categoria.getNome().length() < 3 ){

			throw new ValidacaoException("Precisa ser maior que 3 o nome da categoria" );
		}

		if ( categoria.getId() == null ){

			eM.persist( categoria );

		}else{

			eM.merge( categoria );

		}

		return categoria;

	}

	public void excluirCategoria( Categoria categoria ){

		eM.remove( eM.getReference( Categoria.class , categoria.getId() ) );

	}
}

