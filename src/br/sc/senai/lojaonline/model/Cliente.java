package br.sc.senai.lojaonline.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="clientes_gostam_de_categorias_de_produtos",
			   joinColumns={@JoinColumn(name="cliente_id")},
			   inverseJoinColumns={@JoinColumn(name="categoria_id")})
	private List<Categoria> categoriasQueOClienteGosta;
	
	@OneToMany(mappedBy="cliente")
	private List<Compra> compras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Categoria> getCategoriasQueOClienteGosta() {
		return categoriasQueOClienteGosta;
	}

	public void setCategoriasQueOClienteGosta(List<Categoria> categoriasQueOClienteGosta) {
		this.categoriasQueOClienteGosta = categoriasQueOClienteGosta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
