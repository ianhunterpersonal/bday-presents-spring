package com.totnesjava.bdaypresents.persons;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.totnesjava.bdaypresents.gifts.GiftEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "persons")
public class PersonEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID", strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String				id;

	private String				name;

	private String				email;

	private String				password;

	private String				loginToken;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient", fetch = FetchType.EAGER)
	@Builder.Default
	private List<GiftEntity>	gifts	= new ArrayList<>();

}
