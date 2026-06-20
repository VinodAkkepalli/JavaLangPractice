package java21;

public record UserDeletedEvent(String username, String reason) implements UserEvent {}