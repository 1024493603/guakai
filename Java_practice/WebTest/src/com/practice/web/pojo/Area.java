package com.practice.web.pojo;

public class Area {
    private Integer id;
    private String area;
    private Integer cityId;

    public Area() {
    }

    public Area(Integer id, String area, Integer cityId) {
        this.id = id;
        this.area = area;
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", cityId=" + cityId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
