package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A GeoFence.
 */
@Entity
@Table(name = "geo_fence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeoFence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fence_id")
    private Long fenceID;

    @Column(name = "fence_name")
    private String fenceName;

    @Column(name = "fence_code")
    private String fenceCode;

    @Column(name = "type")
    private Long type;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private ZonedDateTime createdTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private ZonedDateTime modifiedTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFenceID() {
        return fenceID;
    }

    public GeoFence fenceID(Long fenceID) {
        this.fenceID = fenceID;
        return this;
    }

    public void setFenceID(Long fenceID) {
        this.fenceID = fenceID;
    }

    public String getFenceName() {
        return fenceName;
    }

    public GeoFence fenceName(String fenceName) {
        this.fenceName = fenceName;
        return this;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getFenceCode() {
        return fenceCode;
    }

    public GeoFence fenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
        return this;
    }

    public void setFenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
    }

    public Long getType() {
        return type;
    }

    public GeoFence type(Long type) {
        this.type = type;
        return this;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public GeoFence createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public GeoFence createdTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public GeoFence modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedTime() {
        return modifiedTime;
    }

    public GeoFence modifiedTime(ZonedDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public void setModifiedTime(ZonedDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoFence)) {
            return false;
        }
        return id != null && id.equals(((GeoFence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GeoFence{" +
            "id=" + getId() +
            ", fenceID=" + getFenceID() +
            ", fenceName='" + getFenceName() + "'" +
            ", fenceCode='" + getFenceCode() + "'" +
            ", type=" + getType() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedTime='" + getModifiedTime() + "'" +
            "}";
    }
}
