package br.com.api.Forum.exception

import br.com.api.Forum.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
            exception: NotFoundException,
            request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = exception.message,
                path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleParameterNotValidException(
            exception: MethodArgumentNotValidException,
            request: HttpServletRequest
    ): ErrorView {
        val messageError = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
            e -> messageError[e.field] = e.defaultMessage
        }

        return ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = messageError.toString(),
                path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
            exception: Exception,
            request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = exception.message,
                path = request.servletPath
        )
    }
}