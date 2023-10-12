package micro.dao.repository;


import micro.dao.domain.abstractentity.StandardEntity;
import micro.dao.domain.pk.StandardEntityPK;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface TenantRepository<E extends StandardEntity<ID>, ID extends StandardEntityPK> extends StandardRepository<E, ID> {
    List<E> findAllByTenantId(UUID tenantId);
    List<E> deleteAllByTenantId(UUID tenantId);
}
