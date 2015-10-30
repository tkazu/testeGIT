package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import util.Persistencia;
import entity.Pessoa;

public class Teste {

	public static void main(String[] args) {
		TestePersistence dao = new TestePersistence();
		// dao.incluir();
		// dao.pesquisar();
		// dao.testeNamedQuery();
		dao.testeCriteria();
		try {
			dao.indexar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// dao.indexar();
	}
}

class TestePersistence {
	public void incluir() {
		Persistencia dao = new Persistencia();
		EntityTransaction transacao = dao.getEntityManager().getTransaction();

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("maria");
		pessoa.getTelefones().add("(22)8463-4849");

		transacao.begin();
		dao.getEntityManager().persist(pessoa);
		transacao.commit();

	}

	public void pesquisar() {
		Persistencia dao = new Persistencia();
		Pessoa p = dao.getEntityManager().find(Pessoa.class, 202919L);
		System.out.println(p.getNome());
		dao.getEntityManager().close();
	}

	public void testeNamedQuery() {
		List<Pessoa> pessoas;
		Persistencia dao = new Persistencia();
		
		String query = "SELECT p FROM Pessoa p";
		TypedQuery<Pessoa> tqr = dao.getEntityManager().createQuery(query,
				Pessoa.class);
		pessoas = tqr.getResultList();
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getId() + " - " + pessoa.getNome());
		}

		TypedQuery<Pessoa> tqr2 = dao.getEntityManager().createNamedQuery("Pessoa.count", Pessoa.class);
		System.out.println(tqr2.getSingleResult());

		TypedQuery<Pessoa> tqr3 = dao.getEntityManager().createNamedQuery("Pessoa.findById", Pessoa.class);
		tqr3.setParameter("id", 203939L);
		
		pessoas = tqr3.getResultList();
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.toString());
		}
	}

	public void testeCriteria() {
		Persistencia dao = new Persistencia();
		CriteriaBuilder cb = dao.getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
		Root<Pessoa> p = cq.from(Pessoa.class);
		cq.select(p);

		TypedQuery<Pessoa> query = dao.getEntityManager().createQuery(cq);

		List<Pessoa> pessoas = query.getResultList();

		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome());
		}

	}

	public void indexar() throws InterruptedException {
		Persistencia dao = new Persistencia();
		EntityManager manager = dao.getEntityManager();
		FullTextEntityManager ftm = Search.getFullTextEntityManager(manager);
		ftm.createIndexer().startAndWait();
	}

	public void validar() {
		Persistencia dao = new Persistencia();
		Pessoa p = new Pessoa();
	}
	//outra linha
}
