package micro.kafka.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SmsNotificationEvent extends NotificationEvent {
    private final String from;
    private final String to;

    public SmsNotificationEvent() {
        this(null, null, null, null);
    }

    public SmsNotificationEvent(final UUID tenantId, final UUID notificationId, final String from, final String to) {
        super(tenantId, notificationId);
        this.from = from;
        this.to = to;
        this.type = NotificationType.SMS;
    }
}
