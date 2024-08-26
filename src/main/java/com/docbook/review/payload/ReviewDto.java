package com.docbook.review.payload;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    @Min(value = 0,message = "Rating must be between 0 and 5")
    @Max(value = 5,message = "Rating must be between 0 and 5")
    private Double rating;

    @Size(min = 2,message = "description must have at least 2 characters")
    private String description;

    private String doctorName;

    private String PatientName;
}
