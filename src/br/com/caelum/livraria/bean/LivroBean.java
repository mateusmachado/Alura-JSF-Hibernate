package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean(name="livroBean")
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void setAutorId(String autorId) { 
		   System.out.println(autorId); 
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void gravarAutor(){
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public List<Autor> getAutoresDeLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros() {
		  return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if ( livro.getAutores().isEmpty() ) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}
		new DAO<Livro>(Livro.class).adiciona(this.livro);
		this.livro = new Livro();
	}
	
	public void comecaComValorUm( FacesContext fc, UIComponent ui, Object value ) throws ValidationException{
		String conteudo = value.toString();
		if ( !conteudo.startsWith("1") ){
			throw new ValidatorException(new FacesMessage("deve ser um"));			
		}
	}
	
	public void precoValido( FacesContext fc, UIComponent ui, Object value ) throws ValidationException{
		double preco = Double.parseDouble(value.toString());
		if ( !(preco >=1 && preco <= 1000) ){
			throw new ValidatorException(new FacesMessage("Preço deve estar entre R$1,00 e R$1.000,00"));			
		}
	}
	
	public String formAutor(){
		System.out.println("Chamando autor.xhtml");
		return "autor?faces-redirect=true"; 
	}
	

}