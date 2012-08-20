package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


@Entity
@NamedQueries({
	@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p"),
	@NamedQuery(name="Pessoa.count", query="SELECT COUNT(p) FROM Pessoa p"),
	@NamedQuery(name="Pessoa.findById", query="SELECT p FROM Pessoa p WHERE p.id = :id")
})
@Indexed
public class Pessoa {
	/*
	 * @Column() length = Limita a quantidade de caracteres de uma string
	 * nullable = Determina se o campo pode possuir valores null ou não unique =
	 * Determina se uma coluna pode ter valores repetidos ou não precision =
	 * Determina a quantidade de dígitos de umnúmero decimal a serem armazenadas
	 * scale = Determina a quantidade de casas decimais de um número decimal
	 */

	@Id
	@GeneratedValue
	private long id;

	@Field
	private String nome;
	
	@ElementCollection
	private List<String> telefones = new ArrayList<String>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	
	
}
