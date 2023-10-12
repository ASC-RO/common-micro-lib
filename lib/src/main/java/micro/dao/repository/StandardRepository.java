package micro.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StandardRepository<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
