package micro.dao.service;

import lombok.extern.slf4j.Slf4j;
import micro.context.UserContext;
import micro.dao.domain.abstractentity.IdEntity;
import micro.dao.repository.StandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class DaoService<R extends StandardRepository<E, ID>, E extends IdEntity<ID>, ID> extends QueryService<E> {
    @Autowired
    protected R repository;

    /**
     * Save an entity.
     *
     * @param entity the entity to save.
     * @return the persisted entity.
     */
    public E save(E entity) {
        log.debug("Request to save : {}", entity);
        return repository.save(entity);
    }

    /**
     * Save all entities.
     *
     * @param entities the entities to save.
     * @return the persisted entity.
     */
    public List<E> saveAll(List<E> entities) {
        log.debug("Request to save all : {}", entities);
        return repository.saveAll(entities);
    }

    @Transactional(readOnly = true)
    public List<E> findAll() {
        log.debug("Request to get all entities");
        return this.repository.findAllByTenantId(UserContext.getTenantId());
    }

    public Page<E> findBySpecification(Specification<E> specification, Pageable pageable) {
        Specification<E> specificationWithTenant = specification
                .and((entityRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(entityRoot.get("tenantId"), UserContext.getTenantId()));
        return this.repository.findAll(specificationWithTenant, pageable);
    }

    public Optional<E> update(final E entity, final Consumer<E> updateFunction) {
        return update(entity.getEntityId(), updateFunction);
    }
    public Optional<E> update(final ID entityId, final Consumer<E> updateFunction) {
        return this.findOne(entityId).map(entity -> {
            updateFunction.accept(entity);
            return this.save(entity);
        });
    }

    /**
     * Get one entity by id.
     *
     * @param entityId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<E> findOne(ID entityId) {
        log.debug("Request to get entity : {}", entityId);
        return repository.findById(entityId);
    }

    /**
     * Delete the entity by id.
     *
     * @param entityId the id of the entity.
     */
    public void delete(ID entityId) {
        log.debug("Request to delete entity : {}", entityId);
        repository.deleteById(entityId);
    }

    public void delete(Example<E> example) {
        log.debug("Request to delete entity : {}", example.getProbe());
        repository.delete(example.getProbe());
    }

    public void deleteAll(final Collection<E> entities) {
        log.debug("Request to delete entities : {}", entities);
        this.repository.deleteAll(entities);
    }
}
