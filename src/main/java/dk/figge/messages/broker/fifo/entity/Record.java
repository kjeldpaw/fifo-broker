package dk.figge.messages.broker.fifo.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "records")
@RegisterForReflection
public class Record extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "content", nullable = false, updatable = false, columnDefinition = "blob")
    private byte[] content;

    @Column(name = "channel", length = 50, nullable = false, updatable = false)
    private String channel;

    @Column(name = "group", length = 50, updatable = false)
    private String group;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    private Record parent;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "child_id")
    private Record child;

    @Column(name = "state", nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy="record", fetch = FetchType.EAGER)
    private Set<RecordHeader> headers;

    public Record(byte[] content, String channel, Record parent) {
        this.content = content;
        this.channel = channel;
        this.parent = parent;
        this.state = State.Pending;
    }

    protected Record() {
    }

    public String getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public byte[] getContent() {
        return content;
    }

    public String getChannel() {
        return channel;
    }

    public String getGroup() {
        return group;
    }

    public Optional<Record> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setParent(Record nextRecord) {
        this.parent = nextRecord;
    }

    public Optional<Record> getChild() {
        return Optional.ofNullable(child);
    }

    public void setChild(Record child) {
        this.child = child;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @PrePersist
    void prePersist() {
        created = new Timestamp(System.currentTimeMillis());
        modified = created;
    }

    @PreUpdate
    void preUpdate() {
        modified = new Timestamp(System.currentTimeMillis());
    }
}
