package com.totnesjava.bdaypresents.gifts;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@ApiResponses(value = {
		@ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content(schema = @Schema(implementation = Void.class)))
})
@AllArgsConstructor
@RestController
@RequestMapping("/v1/gifts")
public class GiftsController {

	@Operation(summary = "Recovers all gifts in the database")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Gifts listed",
					content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = GiftResource.class)))
			)
	})
	@GetMapping
	@ResponseBody
	public List<GiftEntity> listAllGifts() {
		List<GiftEntity> allGifts = service.findAll();
		return allGifts;
	}

	@Operation(summary = "Create a new gift with given attributes.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "Gift created",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = GiftResource.class))
			)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addGift(@RequestBody GiftEntity giftEntity) {
		service.save(giftEntity);
	}

	private GiftService service;
	
}
