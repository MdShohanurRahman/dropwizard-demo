package org.example.todo;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private final TodoDAO todoDAO;

    public TodoResource(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<TodoRepresentation> getAllTodos() {
        List<TodoEntity> todos = todoDAO.findAll();
        return todos.stream()
                .map(TodoRepresentation::new)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response createTodo(@Valid TodoRepresentation todoRepresentation) {
        TodoEntity todo = todoRepresentation.toTodoEntity();
        TodoEntity createdTodo = todoDAO.create(todo);
        return Response.status(Response.Status.CREATED)
                .entity(new TodoRepresentation(createdTodo))
                .build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public TodoRepresentation getTodoById(@PathParam("id") long id) {
        TodoEntity todo = todoDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));
        return new TodoRepresentation(todo);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public TodoRepresentation updateTodoById(@PathParam("id") long id, @Valid TodoRepresentation updatedTodoRepresentation) {
        TodoEntity todo = todoDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));

        // Update the fields of the existing object
        todo.setTitle(updatedTodoRepresentation.getTitle());
        todo.setCompleted(updatedTodoRepresentation.isCompleted());

        return new TodoRepresentation(todo);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response deleteTodoById(@PathParam("id") long id) {
        TodoEntity todo = todoDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));

        todoDAO.delete(todo);

        return Response.noContent().build();
    }
}