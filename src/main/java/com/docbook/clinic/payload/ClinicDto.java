package com.docbook.clinic.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDto {


    public String clinicName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public long cityId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public long areaId;

    public String areaName;

    public String cityName;

    public long id;
}
