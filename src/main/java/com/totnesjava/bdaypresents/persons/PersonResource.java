package com.totnesjava.bdaypresents.persons;

import java.util.ArrayList;
import java.util.List;

import com.totnesjava.bdaypresents.gifts.GiftResource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Resource representing a person in the system")
@Data
@Builder
@AllArgsConstructor
public class PersonResource {

	@Schema(description = "The ID of the person as a GUID", example = "GUID")
	private String id;
	
	@Schema(description = "Full name of the person", example = "Ian Hunter")
	private String name;
	
	@Schema(description = "The valid email address of the person", example = "test@mail.com")
	private String email;
	
	@Schema(description = "The password for the person", example = "secret")
	private String password;
	
	@Schema(description = "Token assigned when user logged in", example = "0a772b0c-1e5c-4725-94c9-d275724670ec")
	private String loginToken;
	
	@Builder.Default
	private List<GiftResource> gifts = new ArrayList<>();
	
}
