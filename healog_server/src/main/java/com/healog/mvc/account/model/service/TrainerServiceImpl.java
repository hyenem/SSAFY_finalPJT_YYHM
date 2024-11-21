package com.healog.mvc.account.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.healog.mvc.account.model.dao.TrainerDao;
import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.account.model.dto.User;
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

	@Override
	public Trainer checkLogin(String email, String password) {
		Trainer checkingTrainer = trainerDao.selectByEmail(email);
		Trainer loginTrainer = new Trainer();
		//email자체가 없으면 null로 return
		if(checkingTrainer!=null) {
			loginTrainer.setEmail(email);
			//일단 email은 저장
			//그리고 비밀번호 채크한 다음에 나머지 정보들 저장
			String encryptPassword = encryptUtil.getEncrypt(password, checkingTrainer.getSalt());
			if(checkingTrainer.getPassword().equals(encryptPassword)) {
				loginTrainer.setEmail(checkingTrainer.getEmail());
				loginTrainer.setId(checkingTrainer.getId());
			}
		}
		return loginTrainer;
	}

	@Override
	public String getIdByEmail(String email) {
		return trainerDao.selectIdByEmail(email);
	}

	@Override
	public Trainer getTrainerById(String id) {
		Trainer trainer = trainerDao.selectAllById(id);
		if(trainer!=null) {
			trainer.setPassword(null);
			trainer.setSalt(null);
			trainer.setCreatedAtDatetime(null);
		}
		return trainer;
	}

	@Override
	public List<Trainer> getAllTrainer() {
		return trainerDao.selectAllTrainers();
	}

	@Override
	public List<Trainer> searchTrainerByName(String name) {
		return trainerDao.selectTrainersByName(name);
	}
	
}
