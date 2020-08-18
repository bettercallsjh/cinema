package com.pmsj.cinema.common.vo;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CinemaVo {
    private Integer cinemaId;

    private String cinemaName;

    private String cinemaPhone;

    private String cinemaArea;

    private String cinemaLocation;

    private Integer cinemaBrand;

    private String cinemaImg;

    private String cinemaCity;

    private String cinemaLng;

    private String cinemaLat;

    private String cinemaProvince;

    private String brandName;

    private double minMoney;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName == null ? null : cinemaName.trim();
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone == null ? null : cinemaPhone.trim();
    }

    public String getCinemaArea() {
        return cinemaArea;
    }

    public void setCinemaArea(String cinemaArea) {
        this.cinemaArea = cinemaArea == null ? null : cinemaArea.trim();
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation == null ? null : cinemaLocation.trim();
    }

    public Integer getCinemaBrand() {
        return cinemaBrand;
    }

    public void setCinemaBrand(Integer cinemaBrand) {
        this.cinemaBrand = cinemaBrand;
    }

    public String getCinemaImg() {
        return cinemaImg;
    }

    public void setCinemaImg(String cinemaImg) {
        this.cinemaImg = cinemaImg == null ? null : cinemaImg.trim();
    }

    public String getCinemaCity() {
        return cinemaCity;
    }

    public void setCinemaCity(String cinemaCity) {
        this.cinemaCity = cinemaCity == null ? null : cinemaCity.trim();
    }

    public String getCinemaLng() {
        return cinemaLng;
    }

    public void setCinemaLng(String cinemaLng) {
        this.cinemaLng = cinemaLng == null ? null : cinemaLng.trim();
    }

    public String getCinemaLat() {
        return cinemaLat;
    }

    public void setCinemaLat(String cinemaLat) {
        this.cinemaLat = cinemaLat == null ? null : cinemaLat.trim();
    }

    public String getCinemaProvince() {
        return cinemaProvince;
    }

    public void setCinemaProvince(String cinemaProvince) {
        this.cinemaProvince = cinemaProvince == null ? null : cinemaProvince.trim();
    }
}