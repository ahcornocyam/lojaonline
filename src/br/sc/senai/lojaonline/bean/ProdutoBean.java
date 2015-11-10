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

import br.sc.senai.lojaonline.dao.ProdutoDao;
import br.sc.senai.lojaonline.exception.ValidacaoException;
import br.sc.senai.lojaonline.model.Categoria;
import br.sc.senai.lojaonline.model.Produto;

@Named(value="produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	private List<Produto> produtos;

	@Inject
	private CarrinhoBean carrinhoBean;

	@Inject
	private CategoriaBean categoriaBean;

	@Inject
	private ProdutoDao dao;

	@PostConstruct
	public void inicializar(){

		produto = new Produto();

		produtos = dao.listarProdutos();

	}

	public void salvarProduto(){

		try {
			dao.salvarProduto( produto );
			produtos = dao.listarProdutos();

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,produto.getNome(),"Produto salvo com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			produto = new Produto();

		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Deu erro!!",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void editarProduto(Produto produto){
		this.produto = produto;

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LojaOnline/cadastroProduto.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removerProduto(Produto produto){
		this.dao.excluirProduto(produto);
		produtos = dao.listarProdutos();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,produto.getNome(),"Produto removido.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void adicionarAoCarrinho(Produto produto){
		carrinhoBean.getCarrinho().add(produto);

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,produto.getNome(),"Produto adicionado ao carrinho.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public List<Categoria> getCategorias(){
		return categoriaBean.getCategorias();
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}

}
