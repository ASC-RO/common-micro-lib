package micro.dao.domain.abstractentity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class StandardEntity<PK> extends AbstractAuditingEntity<PK> {
    @Id
    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;
}
