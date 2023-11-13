package micro.kafka.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PushNotificationEvent extends NotificationEvent {
    private final String token;
    private String topic;
    private String image;

    public PushNotificationEvent() {
        this(null, null, null);
    }

    public PushNotificationEvent(final UUID tenantId, final UUID notificationId, final String token) {
        super(tenantId, notificationId);
        this.token = token;
        this.type = NotificationType.PUSH;
    }
}
