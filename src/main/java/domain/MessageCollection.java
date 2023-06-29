package domain;

import domain.Contact;
import domain.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class MessageCollection {
    private static volatile MessageCollection instance = null;
    private final SortedMap<MessageKey, Message> messageMap = new TreeMap<>();

    private MessageCollection() {
        // Private constructor to prevent instantiation from other classes
    }

    public Collection<Message> getAllMessages() {
        synchronized (messageMap) {
            return messageMap.values();
        }
    }

    public static MessageCollection getInstance() {
        if (instance == null) {
            synchronized (MessageCollection.class) {
                if (instance == null) {
                    instance = new MessageCollection();
                }
            }
        }
        return instance;
    }

    public void addMessage(Message message) {
        MessageKey key = new MessageKey( message.getToAsUser(), message.getFrom(), message.getDateTime());
        synchronized (messageMap) {
            messageMap.put(key, message);
        }
    }

    public void removeMessage(Message message) {
        synchronized (messageMap) {
            MessageKey key = new MessageKey(message.getToAsUser(), message.getFrom(), message.getDateTime());
            messageMap.remove(key);
        }
    }


    public Message getMessage(User to, Contact from, LocalDateTime received) {
        MessageKey key = new MessageKey(to, from, received);
        synchronized (messageMap) {
            return messageMap.get(key);
        }
    }

    // Inner class representing the key used in the Sorted Map
    // Comparable because it is required by TreeMap
    private static class MessageKey implements Comparable<MessageKey> {
        private final User to;
        private final Contact from;
        private final LocalDateTime received;

        public MessageKey(User to, Contact from, LocalDateTime received) {
            this.to = to;
            this.from = from;
            this.received = received;
        }

        @Override
        public int compareTo(MessageKey other) {
            int toComparison = to.compareTo(other.to);
            if (toComparison != 0) {
                return toComparison;
            }

            int fromComparison = from.compareTo(other.from);
            if (fromComparison != 0) {
                return fromComparison;
            }

            return received.compareTo(other.received);
        }
    }
}
