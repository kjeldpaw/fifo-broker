package dk.figge.messages.broker.fifo.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum State {
    Pending, Processing, Failed, Timeout, Cancelled
}
