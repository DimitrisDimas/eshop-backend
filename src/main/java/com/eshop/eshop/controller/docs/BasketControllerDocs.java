package com.eshop.eshop.controller.docs;

import com.eshop.eshop.dto.BasketResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BasketControllerDocs {

    @Operation(
            summary = "Get all baskets",
            description = "Retrieve all baskets stored in Redis",
            tags = {"Basket"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BasketResponse.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    List<BasketResponse> getAllBaskets();

    @Operation(
            summary = "Get basket by ID",
            description = "Retrieve a basket and its items using basket ID",
            tags = {"Basket"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BasketResponse.class))
                    ),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    BasketResponse getBasketById(@PathVariable("basketId") String basketId);

    @Operation(
            summary = "Delete basket by ID",
            description = "Delete a basket from Redis storage by its ID",
            tags = {"Basket"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    void deleteBasketById(@PathVariable("basketId") String basketId);

    @Operation(
            summary = "Create a new basket",
            description = "Create a basket and store it in Redis",
            tags = {"Basket"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = BasketResponse.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<BasketResponse> createBasket(@RequestBody BasketResponse basketResponse);
}
