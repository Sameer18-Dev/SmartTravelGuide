package com.sameer18dev.smarttravelguide;

    public class city {

        private String name, temprature, humidity, lat, lng;

        public city(String name, String temprature, String humidity, String lat, String lng) {
            this.name = name;
            this.temprature = temprature;
            this.humidity = humidity;
            this.lat = lat;
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public String getTemprature() {
            return temprature;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }
    }
