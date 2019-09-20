package controller;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import webserver.domain.QueryParameter;
import webserver.domain.Request;
import webserver.domain.Response;

import static org.slf4j.LoggerFactory.getLogger;

public class UserController {
    private static final Logger LOG = getLogger(UserController.class);

    public static Response createUser(final Request request) {
        final QueryParameter queries = request.getQueryParameter();
        final String userId = queries.getValue("userId");
        final String password = queries.getValue("password");
        final String name = queries.getValue("name");
        final String email = queries.getValue("email");
        final User user = new User(userId, password, name, email);

        DataBase.addUser(user);
        LOG.debug(user.toString());

        return new Response.Builder().redirectUrl("/index.html").build();
    }
}
