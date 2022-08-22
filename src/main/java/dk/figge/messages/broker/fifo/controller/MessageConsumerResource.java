package dk.figge.messages.broker.fifo.controller;

import dk.figge.messages.broker.fifo.api.Message;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api/v1/consumer")
public class MessageConsumerResource {

    @PUT
    @Path("approve/{messageId}")
    public Uni<Void> approve(@PathParam("messageId") String messageId) {

    }

    @PUT
    @Path("reject/{messageId}")
    public Uni<Void> reject(@PathParam("messageId") String messageId, String message) {

    }

    @PUT
    @Path("cancel/{messageId}")
    public Uni<Void> cancel(@PathParam("messageId") String messageId, String message) {

    }

    @PUT
    @Path("poll/{channel}")
    public Uni<Message> poll(@PathParam("channel") String channel) {

    }

    @PUT
    @Path("poll/{channel}/{group}")
    public Uni<Message> poll(@PathParam("channel") String channel, @PathParam("group") String group) {

    }
}
