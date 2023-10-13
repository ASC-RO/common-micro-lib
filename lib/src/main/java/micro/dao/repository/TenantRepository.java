package micro.dao.repository;


import micro.dao.domain.abstractentity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface TenantRepository<E extends TenantEntity<ID>, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
    List<E> findAllByTenantId(UUID tenantId);
    List<E> deleteAllByTenantId(UUID tenantId);
}
