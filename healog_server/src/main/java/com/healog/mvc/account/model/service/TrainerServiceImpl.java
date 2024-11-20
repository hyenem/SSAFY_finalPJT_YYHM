package com.healog.mvc.account.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.healog.mvc.account.model.dao.TrainerDao;
import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.util.EncryptUtil;

@Service
public class TrainerServiceImpl implements TrainerService{
	
	private TrainerDao trainerDao;
	private EncryptUtil encryptUtil;
	
	public TrainerServiceImpl(TrainerDao trainerDao, EncryptUtil encryptUtil) {
		this.trainerDao = trainerDao;
		this.encryptUtil = encryptUtil;
	}
	
	@Override
	public List<Gym> getAllGyms() {
		return trainerDao.selectAllGyms();
	}

	@Override
	public boolean isValidId(String email) {
		return trainerDao.selectIdByEmail(email)==null || trainerDao.selectIdByEmail(email).length()==0? true : false;
	}

	@Override
	public boolean signUp(Trainer trainer) {
		
		trainer.setId(UUID.randomUUID().toString());
		String salt = encryptUtil.getSalt();
		trainer.setSalt(salt);
		trainer.setPassword(encryptUtil.getEncrypt(trainer.getPassword(), salt));
		
		return trainerDao.insertTrainer(trainer)>0 ? true:false;
	}

	
}
