package com.donat.crypto.dataFetcher;

import com.donat.crypto.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class UserGraphQLDataFetcers {

    private UserRepository userRepository;

    public UserGraphQLDataFetcers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DataFetcher getUser() {
        return dataFetchingEnvironment -> {
            Integer id = dataFetchingEnvironment.getArgument("id");
            return userRepository.findById(id.longValue());
        };
    }
}
