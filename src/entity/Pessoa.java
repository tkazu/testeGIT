package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.sun.istack.internal.NotNull;


@Entity
@NamedQueries({
	@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p"),
	@NamedQuery(name="Pessoa.count", query="SELECT COUNT(p) FROM Pessoa p"),
	@NamedQuery(name="Pessoa.findById", query="SELECT p FROM Pessoa p WHERE p.id = :id")
})
public class Pessoa {
	/*
	 * @Column() length = Limita a quantidade de caracteres de uma string
	 * nullable = Determina se o campo pode possuir valores null ou n�o unique =
	 * Determina se uma coluna pode ter valores repetidos ou n�o precision =
	 * Determina a quantidade de d�gitos de umn�mero decimal a serem armazenadas
	 * scale = Determina a quantidade de casas decimais de um n�mero decimal
	 */

	@Id
	@GeneratedValue
	private long id;
	@NotNull
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
