package micro.kafka.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor(force = true)
public abstract class NotificationEvent {
    private final UUID tenantId;
    private final UUID notificationId;
    private Map<String, Object> dataModel;
    protected NotificationType type;

}
