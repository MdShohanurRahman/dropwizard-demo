package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.example.config.AppConfiguration;
import org.example.resource.HelloResource;
import org.example.todo.TodoDAO;
import org.example.todo.TodoEntity;
import org.example.todo.TodoResource;

public class App extends Application<AppConfiguration> {
    private final HibernateBundle<AppConfiguration> hibernateBundle =
            new HibernateBundle<>(TodoEntity.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) {
        // Configure and register resources, health checks, etc.

        environment.jersey().register(new HelloResource());

        final TodoDAO todoDao = new TodoDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TodoResource(todoDao));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
