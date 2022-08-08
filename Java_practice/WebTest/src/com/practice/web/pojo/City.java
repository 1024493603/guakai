package com.practice.web.pojo;

public class City {
    private Integer id;
    private String city;
    private Integer provinceId;

    public City() {
    }

    public City(Integer id, String city, Integer provinceId) {
        this.id = id;
        this.city = city;
        this.provinceId = provinceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
