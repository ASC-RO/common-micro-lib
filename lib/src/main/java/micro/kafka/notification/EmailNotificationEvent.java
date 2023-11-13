package micro.kafka.notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamSource;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EmailNotificationEvent extends NotificationEvent {
    private final String to;
    private String from;
    private List<AttachmentFile> attachments;
    private boolean isHtml;

    public EmailNotificationEvent() {
        this(null, null, null);
    }

    public EmailNotificationEvent(final UUID tenantId, final UUID notificationId, final String to) {
        super(tenantId, notificationId);
        this.to = to;
        this.type = NotificationType.EMAIL;
    }

    public record AttachmentFile(String attachmentName, InputStreamSource inputStreamSource) { }
}
