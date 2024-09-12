package com.vnpay.controller.errorhandler;

import com.vnpay.dto.response.ErrorBodyResponseDto.FieldError;
import com.vnpay.enumeration.ResponseType;
import com.vnpay.exception.BadRequestException;
import com.vnpay.exception.DataConflictException;
import com.vnpay.exception.DataNotfoundException;
import com.vnpay.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler implements ProblemHandling {

    private static final String MESSAGE_CODE = "messageCode";

    @Override
    public ResponseEntity<Problem> process(
            ResponseEntity<Problem> entity, NativeWebRequest request) {

        Problem problem = entity.getBody();

        if (problem == null) {
            return null;
        }

        if (!(problem instanceof ConstraintViolationProblem
              || problem instanceof DefaultProblem
              || problem instanceof BadRequestException
              || problem instanceof DataConflictException
              || problem instanceof DataNotfoundException
              || problem instanceof UnauthorizedException)) {
            return entity;
        }

        if (problem instanceof ConstraintViolationProblem) {
            return new ResponseEntity<>(buildConstraintViolationProblem(problem), entity.getHeaders(),
                    entity.getStatusCode());
        }
        if (problem instanceof BadRequestException) {
            return new ResponseEntity<>(buildBadRequestProblem(problem), entity.getHeaders(), entity.getStatusCode());
        }
        if (problem instanceof DataConflictException) {
            return new ResponseEntity<>(buildConflictProblem(problem), entity.getHeaders(), entity.getStatusCode());
        }
        if (problem instanceof DataNotfoundException) {
            return new ResponseEntity<>(buildNotFoundProblem(problem), entity.getHeaders(), entity.getStatusCode());
        }
        if (problem instanceof UnauthorizedException) {
            return new ResponseEntity<>(buildUnauthorizedProblem(problem), entity.getHeaders(), entity.getStatusCode());
        }
        return new ResponseEntity<>(buildUnknownExceptionProblem(problem), entity.getHeaders(), entity.getStatusCode());
    }

    private void rebuildProblem(ProblemBuilder builder) {
        builder.withType(ResponseType.BAD_REQUEST.getType())
                .withDetail("Bad Request")
                .with(MESSAGE_CODE, ResponseType.BAD_REQUEST.getMessageCode())
                .withStatus(Status.BAD_REQUEST);
    }

    private Problem buildConstraintViolationProblem(Problem problem) {
        ProblemBuilder builder = Problem.builder()
                .with("fieldErrors", ((ConstraintViolationProblem) problem)
                        .getViolations().stream()
                        .map(FieldError::new).toList());
        rebuildProblem(builder);
        return builder.build();
    }

    private Problem buildBadRequestProblem(Problem problem) {
        String msgCode = problem.getTitle();
        return Problem.builder().withType(
                        Problem.DEFAULT_TYPE.equals(problem.getType())
                                ? ResponseType.BAD_REQUEST.getType()
                                : problem.getType())
                .withDetail("Bad Request")
                .with("fieldErrors", msgCode != null ? msgCode : "")
                .withStatus(Status.BAD_REQUEST).build();
    }

    private Problem buildNotFoundProblem(Problem problem) {
        String msgCode = problem.getTitle();
        return Problem.builder().withType(
                        Problem.DEFAULT_TYPE.equals(problem.getType())
                                ? ResponseType.NOT_FOUND.getType()
                                : problem.getType())
                .withDetail("Not Found")
                .with(MESSAGE_CODE, msgCode != null ? msgCode : "")
                .withStatus(Status.NOT_FOUND).build();
    }

    private Problem buildConflictProblem(Problem problem) {
        String msgCode = problem.getTitle();
        return Problem.builder().withType(
                        Problem.DEFAULT_TYPE.equals(problem.getType())
                                ? ResponseType.CONFLICT.getType()
                                : problem.getType())
                .withDetail("Conflict")
                .with(MESSAGE_CODE, msgCode != null ? msgCode : "")
                .withStatus(Status.CONFLICT).build();
    }

    private Problem buildUnauthorizedProblem(Problem problem) {
        String msgCode = problem.getTitle();
        return Problem.builder().withType(
                        Problem.DEFAULT_TYPE.equals(problem.getType())
                                ? ResponseType.UNAUTHORIZED.getType()
                                : problem.getType())
                .withDetail("Unauthorized")
                .with(MESSAGE_CODE, msgCode != null ? msgCode : "")
                .withStatus(Status.UNAUTHORIZED).build();
    }

    private Problem buildUnknownExceptionProblem(Problem problem) {
        return Problem.builder().withType(
                        Problem.DEFAULT_TYPE.equals(problem.getType())
                                ? ResponseType.UNAUTHORIZED.getType()
                                : problem.getType())
                .withDetail("Internal Server Error")
                .with(MESSAGE_CODE, "error.internal.server")
                .withStatus(Status.INTERNAL_SERVER_ERROR).build();
    }
}
