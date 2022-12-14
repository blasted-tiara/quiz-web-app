package ba.fet.rwa.api;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ba.fet.rwa.model.ModelApiResponse;
import ba.fet.rwa.model.Quiz;
import ba.fet.rwa.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Path("/admin")
public class AdminQuizApi {
    @GET
    @Path("/quizzes")
    @Produces({ "application/json" })
    @Operation(summary = "Returns all quizzes", description = "Returns an array of all quizzes user has access to", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Quiz.class)))),
        @ApiResponse(responseCode = "401", description = "Unauthorized quiz access") })
    public List<Quiz> getAllQuizzes(@Context SecurityContext securityContext) throws NotFoundException {
        return QuizService.getAllQuizzes();
    }

    @POST
    @Path("/quizzes")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @Operation(summary = "Add new quiz", description = "", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Quiz created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Quiz.class))) })
    public Quiz addQuiz(
        @Parameter(description = "") @FormParam("title") String title,
        @Context SecurityContext securityContext) throws NotFoundException
    {
        return QuizService.createNewQuiz(title);
    }

    @DELETE
    @Path("/quizzes/{id}")
    @Operation(summary = "Delete quiz by ID", description = "", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
    public Response deleteQuiz(
        @Parameter(in = ParameterIn.PATH, description = "Quiz ID", required = true) @PathParam("id") Long id,
        @Context SecurityContext securityContext)
        throws NotFoundException
    {
        try {
            QuizService.deleteQuiz(id);
            return Response.status(204).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }
    
    @PUT
    @Path("/quizzes/{id}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @Operation(summary = "Update quiz by ID", description = "", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Quiz.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
    public Quiz updateQuiz(
        @Parameter(in = ParameterIn.PATH, description = "Quiz ID", required = true) @PathParam("id") Long id,
        @Parameter(description = "") @FormParam("title") String title,
        @Context SecurityContext securityContext)
        throws NotFoundException
    {
        return QuizService.updateQuiz(id, title);
    }
    
    @GET
    @Path("/quizzes/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Returns quiz by ID", description = "Returns a quiz by ID", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Quiz.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Resource not found") })
    public Quiz getQuizById(
        @Parameter(in = ParameterIn.PATH, description = "Quiz ID", required = true) @PathParam("id") Long id,
        @Context SecurityContext securityContext)
        throws NotFoundException
    {
        return QuizService.getQuizById(id);
    }
    
    @POST
    @Path("/quizzes/{id}/uploadImage")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(summary = "Uploads new quiz image", description = "", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "quiz" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image uploaded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class)))})
    public Response uploadFile(
        @Parameter(in = ParameterIn.PATH, description = "Quiz ID", required = true) @PathParam("id") Long id,
        @FormDataParam("file") InputStream fileInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail,
        @Context SecurityContext securityContext)
        throws NotFoundException
    {
        try {
            QuizService.saveImage(id, fileInputStream, fileDetail);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }
}
