package micro.context;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserInfo {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private String email;
    private String username;
    private String name;
    private String image;
    private String role;
    private UUID tenantId;
    private UUID customerId;

    public void setCreatedAt(final String createdAt) {
        this.createdAt = Instant.parse(createdAt);
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = Instant.parse(updatedAt);
    }
}
