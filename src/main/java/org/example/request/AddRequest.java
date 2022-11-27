package org.example.request;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)


@Data
public class AddRequest {
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private List<Value> values = new ArrayList<Value>();

    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    @JsonProperty("slot")
    public Integer getSlot() {
        return slot;
    }

    @JsonProperty("slot")
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("value")
    public List<Value> getValues() {
        return values;
    }

    @JsonProperty("value")
    public void setValues(List<Value> values) {
        this.values = values;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public static class Value {
        @JsonProperty("status")
        private String status;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("servings")
        private Integer servings;
        @JsonProperty("title")
        private String title;
        @JsonProperty("imageType")
        private String imageType;

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("servings")
        public Integer getServings() {
            return servings;
        }

        @JsonProperty("servings")
        public void setServings(Integer servings) {
            this.servings = servings;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("imageType")
        public String getImageType() {
            return imageType;
        }

        @JsonProperty("imageType")
        public void setImageType(String imageType) {
            this.imageType = imageType;
        }
    }

}
