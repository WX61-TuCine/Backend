package com.upc.TuCine.TuCine.dto.save.Business;

import lombok.Data;

@Data
public class BusinessSaveDto {
    private String name;
    private String socialReason;
    private String ruc;
    private String phone;
    private String email;
    private String imageLogo;
    private String imageBanner;
    private String description;
    private String dateAttention;
    private String address;
    private String referenceAddress;
    private BusinessOwnerSaveDto owner;
    private BusinessTypeSaveDto businessType;
}
