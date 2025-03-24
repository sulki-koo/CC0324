package cookcloud.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cookcloud.entity.Allergy;
import cookcloud.repository.AllergyRepository;
import cookcloud.service.AllergyService;

@Service
public class AllergyServiceImpl implements AllergyService {

	@Autowired
	private AllergyRepository allergyRepository;
	
	public List<Allergy> getAllAllergies(){
		return allergyRepository.findAll();
	}
	
}
