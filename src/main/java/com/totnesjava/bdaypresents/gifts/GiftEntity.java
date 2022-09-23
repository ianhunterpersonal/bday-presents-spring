package com.totnesjava.bdaypresents.gifts;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.totnesjava.bdaypresents.persons.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gifts")
public class GiftEntity {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(
       name = "UUID",
       strategy = "org.hibernate.id.UUIDGenerator"
   )
	private String id;

	private String title;
	private String description;
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient_id")
	private PersonEntity recipient;
	
	private GiftMultiplicity multiplicity;
	
}
