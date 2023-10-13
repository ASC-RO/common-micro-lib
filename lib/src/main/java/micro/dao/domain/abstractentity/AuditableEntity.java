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
public abstract class AuditableEntity {
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
}
