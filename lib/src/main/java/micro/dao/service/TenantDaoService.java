package micro.dao.service;

import lombok.extern.slf4j.Slf4j;
import micro.dao.domain.abstractentity.TenantEntity;
import micro.dao.repository.TenantRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Transactional
@Slf4j
public abstract class TenantDaoService<R extends TenantRepository<E, ID>, E extends TenantEntity<ID>, ID> extends DaoService<R, E, ID> {
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
        return this.findOne(entityId).map(entity -> {
            entity.setTenantId(userContext.tenantId());
            updateFunction.accept(entity);
            return this.save(entity);
        });
    }

    @Override
    public Page<E> findBySpecification(Specification<E> specification, Pageable pageable) {
        return super.findBySpecification(specificationWithTenant(specification), pageable);
    }

    @Override
    public Optional<E> findOne(ID entityId) {
        return repository.findOne(specificationWithTenant(entityId));
    }

    @Override
    public boolean existsById(ID entityId) {
        return repository.exists(specificationWithTenant(entityId));
    }

    public long delete(ID entityId) {
        return repository.delete(specificationWithTenant(entityId));
    }

    @Override
    public void delete(E entity) {
        entity.setTenantId(userContext.tenantId());
        super.delete(entity);
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

    protected abstract Specification<E> entityIdSpecification(ID entityId);

    protected Specification<E> specificationWithTenant(ID entityId) {
        return specificationWithTenant(entityIdSpecification(entityId));
    }

    protected Specification<E> specificationWithTenant(Specification<E> specification) {
        return specification.and((entityRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(entityRoot.get("tenantId"), userContext.tenantId())
        );
    }
}
