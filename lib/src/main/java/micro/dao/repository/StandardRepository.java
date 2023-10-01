package micro.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface StandardRepository<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
    List<E> findAllByTenantId(UUID tenantId);
    List<E> deleteAllByTenantId(UUID tenantId);
}
