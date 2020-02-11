package com.donat.crypto.dataFetcher;

import com.donat.crypto.domain.Candle;
import com.donat.crypto.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGraphQLDataFetcers {

    private UserRepository userRepository;

    public UserGraphQLDataFetcers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public DataFetcher getUser() {
        return dataFetchingEnvironment -> {
            Long id = dataFetchingEnvironment.getArgument("id");
            return userRepository.findById(id);
        };
    }
}
