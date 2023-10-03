package micro.dao.service;

import micro.dao.criteria.Criteria;
import micro.dao.domain.abstractentity.IdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface FilterableService<E extends IdEntity<?>, EC extends Criteria, ER> {
    Page<ER> findByCriteria(EC criteria, Pageable pageable);
    Specification<E> customSpecification(EC criteria);
    default Specification<E> createSpecification(EC criteria) {
        return Optional.ofNullable(criteria).map(this::customSpecification).orElse(Specification.where(null));
    }
}
