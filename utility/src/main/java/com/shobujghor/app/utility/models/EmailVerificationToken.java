package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.shobujghor.app.utility.constants.TableNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.EMAIL_VERIFICATION_TOKEN_TABLE)
public class EmailVerificationToken {

    @DynamoDBHashKey
    private String token;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime tokenExpiryDate;

    public static class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public String convert(LocalDateTime object) {
            return object.format(FORMATTER); // Convert LocalDateTime to ISO-8601 String
        }

        @Override
        public LocalDateTime unconvert(String value) {
            return LocalDateTime.parse(value, FORMATTER); // Convert ISO-8601 String back to LocalDateTime
        }
    }
}
