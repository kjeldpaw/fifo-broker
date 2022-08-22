package dk.figge.messages.broker.fifo.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record_headers")
@RegisterForReflection
public class RecordHeader {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "name", length = 50, updatable = false)
    private String name;

    @Column(name = "value", length = 2048)
    private String value;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @PrePersist
    void prePersist() {
        created = new Timestamp(System.currentTimeMillis());
        modified = created;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @PreUpdate
    void preUpdate() {
        modified = new Timestamp(System.currentTimeMillis());
    }

}
