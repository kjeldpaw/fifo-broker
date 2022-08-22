package dk.figge.messages.broker.fifo.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record Message(@NotEmpty @UUID String id, @NotEmpty String channel, String group, @NotNull byte[] content, Set<MessageHeader> headers) {
}
