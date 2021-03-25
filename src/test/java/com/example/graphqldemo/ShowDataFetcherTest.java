package com.example.graphqldemo;

import com.example.graphqldemo.client.ShowGraphQLQuery;
import com.example.graphqldemo.client.ShowProjectionRoot;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DgsAutoConfiguration.class, ShowDataFetcher.class})
class ShowDataFetcherTest {

    @Autowired
    DgsQueryExecutor queryExecutor;

    @Test
    public void withFilter () {
        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
                ShowGraphQLQuery.newRequest().titleFilter("Oz").build(),
                new ShowProjectionRoot().title()
        );
        String query = graphQLQueryRequest.serialize();
        List<String> titles = queryExecutor.executeAndExtractJsonPath(query, "data.show[*].title");
        assertThat(titles).containsExactly("Ozark");
    }
}