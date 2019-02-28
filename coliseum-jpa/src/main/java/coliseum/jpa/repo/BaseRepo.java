package coliseum.jpa.repo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import coliseum.jpa.SearchObject;

@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public List<T> search(SearchObject searchObject);

	public List<Tuple> search(SearchObject searchObject, List<String> selections);

}
