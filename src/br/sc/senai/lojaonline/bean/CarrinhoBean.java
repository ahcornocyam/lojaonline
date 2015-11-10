package br.sc.senai.lojaonline.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.sc.senai.lojaonline.model.Produto;

@Named(value="carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Produto> carrinho;		

	@PostConstruct
	public void inicializar(){
		carrinho = new ArrayList<>();
	}
	
	public List<Produto> getCarrinho(){
		return carrinho;
	}
	
	public String corTexto(){
		
		String estilo = "";
		
		if (calcularValorTotal() > 0) {
			estilo = "color:green;";
		}
		
		return estilo;
		
	}
	
	public Double calcularValorTotal(){
		
		BigDecimal total = BigDecimal.ZERO;
		
		for (Produto produto : getCarrinho()) {
			total = total.add(produto.getPreco());
		}
		
		return total.doubleValue();
	}
		
}
