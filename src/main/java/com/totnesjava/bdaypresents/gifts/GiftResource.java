package com.totnesjava.bdaypresents.gifts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GiftResource {

	@Schema(description = "The system ID for the gift as a GUID", example = "GUID")
	private String id;
	
	@Schema(description = "The short title for the gift", example = "Gift Title")
	private String title;
	
	@Schema(description = "The description of the gift", example = "Gift Description")
	private String description;
	
	@Schema(description = "An optional URL to web resource (e.g. Amazon)", example = "http://gift_shop/gift")
	private String url;
	
	@Schema(description = "How many of this gift are acceptable")
	private GiftMultiplicity multiplicity;
	
	@Schema(description = "An optional URL to web resource (e.g. Amazon)", example = "http://gift_shop/gift")
	private String recipientId;
	
}
