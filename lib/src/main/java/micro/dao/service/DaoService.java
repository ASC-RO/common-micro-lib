package micro.dao.service;

import lombok.extern.slf4j.Slf4j;
import micro.context.UserContext;
import micro.dao.domain.abstractentity.IdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Transactional
@Slf4j
public abstract class DaoService<R extends JpaRepository<E, ID> & JpaSpecificationExecutor<E>, E extends IdEntity<ID>, ID> extends QueryService<E> {
    @Autowired
    protected R repository;
    @Autowired
    protected UserContext userContext;

    /**
     * Save an entity.
     *
     * @param entity the entity to save.
     * @return the persisted entity.
     */
    public E save(E entity) {
        return repository.save(entity);
    }

    /**
     * Save all entities.
     *
     * @param entities the entities to save.
     * @return the persisted entity.
     */
    public List<E> saveAll(List<E> entities) {
        return repository.saveAll(entities);
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

    public Page<E> findBySpecification(Specification<E> specification, Pageable pageable) {
        return this.repository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Optional<E> findOne(ID entityId) {
        return repository.findById(entityId);
    }

    /**
     * Delete the entity.
     *
     * @param entity the entity.
     */
    public void delete(E entity) {
        repository.delete(entity);
    }

    public void delete(Example<E> example) {
        repository.delete(example.getProbe());
    }

    public void deleteBy(ID entityId) {
        repository.deleteById(entityId);
    }

    public void deleteAll(final Collection<E> entities) {
        this.repository.deleteAll(entities);
    }
}
