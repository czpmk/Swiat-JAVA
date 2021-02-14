package labO.world.messages;

public class Message implements Comparable<Message> {
    private final int importance_;
    private final String value_;

    public Message(int importance, String value) {
        importance_ = importance;
        value_ = value;
    }

    public final int getImportance() {
        return importance_;
    }

    public final String getValue() {
        return value_;
    }

    @Override
    public int compareTo(Message secondMessage) {
        return Integer.compare(importance_, secondMessage.getImportance());
    }
}
