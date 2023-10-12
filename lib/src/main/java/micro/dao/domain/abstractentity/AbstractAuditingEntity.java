package micro.dao.domain.abstractentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity<PK> implements IdEntity<PK> {
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    @CreationTimestamp
    @Column(name = "created_date", columnDefinition = "timestamptz")
    protected OffsetDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @UpdateTimestamp
    @Column(name = "last_modified_date", columnDefinition = "timestamptz")
    protected OffsetDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAuditingEntity<?> that = (AbstractAuditingEntity<?>) o;

        return this.getEntityId().equals(that.getEntityId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
