package ba.fet.rwa.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ba.fet.rwa.model.Question;
import ba.fet.rwa.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Path("/admin")
public class AdminQuestion {
    // Create new question for quiz
    @POST
    @Path("/quizzes/{quizId}/questions")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @Operation(summary = "Add new question", description = "", security = {
        @SecurityRequirement(name = "user_auth") }, tags = { "question" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Question created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Question.class))) })
    public Question addQuestion(
        @Parameter(in = ParameterIn.PATH, description = "Quiz ID", required = true) @PathParam("quizId") Long quizId,
        @Parameter(description = "") @FormParam("text") String text,
        @Parameter(description = "") @FormParam("time") Integer time,
        @Parameter(description = "") @FormParam("points") Integer points, @Context SecurityContext securityContext)
        throws NotFoundException
    {
        return QuestionService.createNewQuestion(quizId, text, time, points);
    }

}
