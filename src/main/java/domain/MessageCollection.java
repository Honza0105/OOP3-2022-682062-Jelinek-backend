package domain;


import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This is a class with only one instance.
 * It behaves like a Sorted Map with as its
 * KEY!!!!
 * the combination of a user (the To), a Contact (the From) and a LocalDateTime (received.)
 * Make sure access to the data stored in this object stays consistent in a multithreaded situation!
 */

public class MessageCollection {
    private static volatile MessageCollection instance = null;
    private SortedMap<MessageKey, Message> messageMap = new TreeMap<>();

    private MessageCollection() {
        // Private constructor to prevent instantiation from other classes
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

    public void addMessage(User to, Contact from, LocalDateTime received, Message message) {
        MessageKey key = new MessageKey(to, from, received);
        synchronized (messageMap) {
            messageMap.put(key, message);
        }
    }

    public Message getMessage(User to, Contact from, LocalDateTime received) {
        MessageKey key = new MessageKey(to, from, received);
        synchronized (messageMap) {
            return messageMap.get(key);
        }
    }

    // Inner class representing the key used in the Sorted Map
    // Comparable because it is required by treemap
    private static class MessageKey implements Comparable<MessageKey> {
        private User to;
        private Contact from;
        private LocalDateTime received;

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
