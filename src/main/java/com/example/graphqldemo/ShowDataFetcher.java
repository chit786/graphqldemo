package com.example.graphqldemo;

import com.example.graphqldemo.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ShowDataFetcher {

    private List<Show> showData = List.of(
            Show.newBuilder().title("Ozark").rating(5).build(),
            Show.newBuilder().title("Stranger Things").rating(5).build()
            );

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Show)
    public List<Show> shows(@InputArgument String titleFilter) {
        if(titleFilter != null) {
            return showData.stream().filter(s -> s.getTitle().startsWith(titleFilter)).collect(Collectors.toList());
        }

        return showData;
    }
}
