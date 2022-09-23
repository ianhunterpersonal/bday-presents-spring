package com.totnesjava.bdaypresents.gifts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.totnesjava.bdaypresents.persons.PersonEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface GiftMapper {

	GiftMapper INSTANCE = Mappers.getMapper( GiftMapper.class );
	
	@Mapping(source = "recipient", target = "recipientId", qualifiedByName = "getRecipientId")
   GiftResource map(GiftEntity entity);
   
	@Mapping(target = "recipient", ignore = true)
   GiftEntity map(GiftResource resource);
   
   @Named("getRecipientId") 
   public static String getRecipientId(PersonEntity personEntity) { 
       return (personEntity != null) ? personEntity.getId() : null; 
   }
   
}
