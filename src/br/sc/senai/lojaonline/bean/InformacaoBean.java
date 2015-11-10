package br.sc.senai.lojaonline.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value="informacaoBean")
@ApplicationScoped
public class InformacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeAplicao;
	private String versao;
	private String dataAplicacao;
	
	@PostConstruct
	public void inicializar(){
		
		nomeAplicao = "LojaOnline";
		versao = "1.0";
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 dataAplicacao = simpleDateFormat.format(new Date());
		
	}
	
	public String getNomeAplicao() {
		return nomeAplicao;
	}
	public void setNomeAplicao(String nomeAplicao) {
		this.nomeAplicao = nomeAplicao;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public String getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(String dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	
}
