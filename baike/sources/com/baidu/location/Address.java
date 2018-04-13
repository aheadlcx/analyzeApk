package com.baidu.location;

public final class Address {
    public final String address;
    public final String city;
    public final String cityCode;
    public final String country;
    public final String countryCode;
    public final String district;
    public final String province;
    public final String street;
    public final String streetNumber;

    public static class Builder {
        private static final String BEI_JING = "北京";
        private static final String CHONG_QIN = "重庆";
        private static final String SHANG_HAI = "上海";
        private static final String TIAN_JIN = "天津";
        private String mAddress = null;
        private String mCity = null;
        private String mCityCode = null;
        private String mCountry = null;
        private String mCountryCode = null;
        private String mDistrict = null;
        private String mProvince = null;
        private String mStreet = null;
        private String mStreetNumber = null;

        public Address build() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.mCountry != null) {
                stringBuffer.append(this.mCountry);
            }
            if (this.mProvince != null) {
                stringBuffer.append(this.mProvince);
            }
            if (!(this.mProvince == null || this.mCity == null || ((this.mProvince.contains(BEI_JING) && this.mCity.contains(BEI_JING)) || ((this.mProvince.contains(SHANG_HAI) && this.mCity.contains(SHANG_HAI)) || ((this.mProvince.contains(TIAN_JIN) && this.mCity.contains(TIAN_JIN)) || (this.mProvince.contains(CHONG_QIN) && this.mCity.contains(CHONG_QIN))))))) {
                stringBuffer.append(this.mCity);
            }
            if (this.mDistrict != null) {
                stringBuffer.append(this.mDistrict);
            }
            if (this.mStreet != null) {
                stringBuffer.append(this.mStreet);
            }
            if (this.mStreetNumber != null) {
                stringBuffer.append(this.mStreetNumber);
            }
            if (stringBuffer.length() > 0) {
                this.mAddress = stringBuffer.toString();
            }
            return new Address();
        }

        public Builder city(String str) {
            this.mCity = str;
            return this;
        }

        public Builder cityCode(String str) {
            this.mCityCode = str;
            return this;
        }

        public Builder country(String str) {
            this.mCountry = str;
            return this;
        }

        public Builder countryCode(String str) {
            this.mCountryCode = str;
            return this;
        }

        public Builder district(String str) {
            this.mDistrict = str;
            return this;
        }

        public Builder province(String str) {
            this.mProvince = str;
            return this;
        }

        public Builder street(String str) {
            this.mStreet = str;
            return this;
        }

        public Builder streetNumber(String str) {
            this.mStreetNumber = str;
            return this;
        }
    }

    private Address(Builder builder) {
        this.country = builder.mCountry;
        this.countryCode = builder.mCountryCode;
        this.province = builder.mProvince;
        this.city = builder.mCity;
        this.cityCode = builder.mCityCode;
        this.district = builder.mDistrict;
        this.street = builder.mStreet;
        this.streetNumber = builder.mStreetNumber;
        this.address = builder.mAddress;
    }
}
