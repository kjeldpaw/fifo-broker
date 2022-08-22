package dk.figge.messages.broker.fifo.controller;

import dk.figge.messages.broker.fifo.api.Message;
import io.smallrye.mutiny.Uni;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/producer")
public class MessageProducerResource {


    @POST
    @Path("{channel}")
    public Uni<Void> produce(@Valid @NotNull Message message) {
        return null;
    }

}
