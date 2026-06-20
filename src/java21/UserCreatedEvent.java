package java21;

public record UserCreatedEvent(String username, String email) implements UserEvent {}