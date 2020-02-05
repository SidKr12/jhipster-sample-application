package com.mycompany.myapp.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.GeoFence} entity.
 */
public class GeoFenceDTO implements Serializable {

    private Long id;

    private Long fenceID;

    private String fenceName;

    private String fenceCode;

    private Long type;

    private String createdBy;

    private ZonedDateTime createdTime;

    private String modifiedBy;

    private ZonedDateTime modifiedTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFenceID() {
        return fenceID;
    }

    public void setFenceID(Long fenceID) {
        this.fenceID = fenceID;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getFenceCode() {
        return fenceCode;
    }

    public void setFenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(ZonedDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeoFenceDTO geoFenceDTO = (GeoFenceDTO) o;
        if (geoFenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geoFenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeoFenceDTO{" +
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
