package com.donat.crypto.service;

import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;

import com.donat.crypto.dataFetcher.UserGraphQLDataFetcers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.donat.crypto.dataFetcher.CryptoGraphQLDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	private CryptoGraphQLDataFetchers cryptoGraphQLDataFetchers;
	private UserGraphQLDataFetcers userGraphQLDataFetcers;
	@Value("classpath:crypto.graphql")
	Resource resource;
	private GraphQL graphQL;
	@Autowired
	public GraphQLService(CryptoGraphQLDataFetchers cryptoGraphQLDataFetchers,
						  UserGraphQLDataFetcers userGraphQLDataFetcers) {
		this.cryptoGraphQLDataFetchers = cryptoGraphQLDataFetchers;
		this.userGraphQLDataFetcers = userGraphQLDataFetcers;
	}
	@PostConstruct
	private void loadSchema() throws IOException {
		File file = resource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator()
			.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}
	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
			.type("Query", typeWiring -> typeWiring
				.dataFetcher("candleHistory", cryptoGraphQLDataFetchers.getCandles())
				.dataFetcher("actualCandles", cryptoGraphQLDataFetchers.getRecentCandles())
				.dataFetcher("user", userGraphQLDataFetcers.getUser()))
		.build();
	}
	public GraphQL getGraphQL(){
		return graphQL;
	}
}
