package br.com.scherer.pmanager.infrastructure.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class SortProperties {

    private final List<String> ALLOWED_PROPERTIES = List.of(
            "title",
            "numberOfDays",
            "status"
    );

    private final List<String> sortPropertiesList;

    public SortProperties(String commaSeparatedString) {
        sortPropertiesList = Arrays
                .stream(commaSeparatedString.split(","))
                .filter(ALLOWED_PROPERTIES::contains)
                .toList();

    }
}
