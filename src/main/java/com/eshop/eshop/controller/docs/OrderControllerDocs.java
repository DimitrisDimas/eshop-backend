package com.eshop.eshop.controller.docs;


import com.eshop.eshop.dto.orderDto.OrderDto;
import com.eshop.eshop.dto.orderDto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderControllerDocs {

    @Operation(
            summary = "Get order by ID",
            description = "Retrieve an order using its unique ID",
            tags = {"Order"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))
                    ),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId);

    @Operation(
            summary = "Get all orders",
            description = "Retrieve a list of all orders",
            tags = {"Order"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<OrderResponse>> getAllOrders();

    @Operation(
            summary = "Delete an order",
            description = "Delete an order by its ID",
            tags = {"Order"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId);

    @Operation(
            summary = "Create a new order",
            description = "Create an order from supplied order data",
            tags = {"Order"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Integer.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Integer> createOrder(@RequestBody OrderDto orderDto);

    @Operation(
            summary = "Get orders with pagination",
            description = "Retrieve paginated list of orders using Pageable parameters",
            tags = {"Order"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Page<OrderResponse>> getAllOrdersPaged(Pageable pageable);
}

