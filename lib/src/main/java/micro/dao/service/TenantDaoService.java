package micro.dao.service;

import lombok.extern.slf4j.Slf4j;
import micro.dao.domain.abstractentity.StandardEntity;
import micro.dao.domain.pk.StandardEntityPK;
import micro.dao.repository.TenantRepository;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Transactional
@Slf4j
public abstract class TenantDaoService<R extends TenantRepository<E, ID>, E extends StandardEntity<ID>, ID extends StandardEntityPK> extends DaoService<R, E, ID> {
    @Override
    public E save(E entity) {
        entity.setTenantId(userContext.tenantId());
        return super.save(entity);
    }

    @Override
    public List<E> saveAll(List<E> entities) {
        entities.forEach(entity -> entity.setTenantId(userContext.tenantId()));
        return super.saveAll(entities);
    }

    @Override
    public Optional<E> update(final ID entityId, final Consumer<E> updateFunction) {
        entityId.setTenantId(userContext.tenantId());
        return super.update(entityId, updateFunction);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<E> findOne(ID entityId) {
        entityId.setTenantId(userContext.tenantId());
        return super.findOne(entityId);
    }

    @Override
    public void delete(ID entityId) {
        entityId.setTenantId(userContext.tenantId());
        super.delete(entityId);
    }

    @Override
    public void delete(Example<E> example) {
        example.getProbe().setTenantId(userContext.tenantId());
        super.delete(example);
    }

    @Override
    public void deleteAll(final Collection<E> entities) {
        final List<E> tenantEntities = entities.stream()
                .filter(entity -> entity.getTenantId().equals(userContext.tenantId()))
                .toList();
        super.deleteAll(tenantEntities);
    }
}
