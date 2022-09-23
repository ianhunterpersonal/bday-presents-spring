package com.totnesjava.bdaypresents.gifts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Service
public class GiftService {
	
	public List<GiftEntity> findAll() {
		return repository.findAll();
	}

	public void save(GiftEntity giftEntity) {
		repository.save(giftEntity);
	}

	@Autowired
	private GiftRepository repository;

}
