package java21;

public record UserSuspendedEvent(String username, int durationDays) implements UserEvent {}