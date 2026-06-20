package java21;

public sealed interface UserEvent
        permits UserCreatedEvent, UserDeletedEvent, UserSuspendedEvent {
}
