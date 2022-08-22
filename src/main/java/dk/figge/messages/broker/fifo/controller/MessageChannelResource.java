package dk.figge.messages.broker.fifo.controller;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/api/v1/messages/{channel}")
@ApplicationScoped
public class MessageChannelResource {
    private static final Logger logger = Logger.getLogger(MessageChannelResource.class);
    private Map<SessionKey, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("channel") String channel) {
        sessions.put(new SessionKey(session.getId(), channel), session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("channel") String channel) {
        sessions.remove(new SessionKey(session.getId(), channel));
    }

    @OnError
    public void onError(Session session, @PathParam("channel") String channel, Throwable throwable) {
        logger.warnf(throwable, "Session failed: %s", throwable.getMessage());
        sessions.remove(new SessionKey(session.getId(), channel));
    }

    @OnMessage
    public void onMessage(InputStream message, @PathParam("channel") String channel) {

    }

    private record SessionKey(String sessionId, String channel) {

    }
}
