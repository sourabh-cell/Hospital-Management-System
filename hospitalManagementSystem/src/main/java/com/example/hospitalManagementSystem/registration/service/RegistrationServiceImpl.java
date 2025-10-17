package com.example.hospitalManagementSystem.registration.service;

import com.example.hospitalManagementSystem.accountant.AccountantRepo;
import com.example.hospitalManagementSystem.accountant.entity.Accountant;
import com.example.hospitalManagementSystem.accountant.mapper.AccountantMapper;
import com.example.hospitalManagementSystem.authentication.entity.Address;
import com.example.hospitalManagementSystem.authentication.entity.Role;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.entity.UserInfo;
import com.example.hospitalManagementSystem.authentication.repositories.AddressRepo;
import com.example.hospitalManagementSystem.authentication.repositories.RoleRepo;
import com.example.hospitalManagementSystem.authentication.repositories.UserRepo;
import com.example.hospitalManagementSystem.doctor.entity.Doctor;
import com.example.hospitalManagementSystem.doctor.mapper.DoctorMapper;
import com.example.hospitalManagementSystem.doctor.repo.DoctorRepo;
import com.example.hospitalManagementSystem.head_nurse.entity.HeadNurse;
import com.example.hospitalManagementSystem.head_nurse.mapper.HeadNurseMapper;
import com.example.hospitalManagementSystem.head_nurse.repo.HeadNurseRepository;
import com.example.hospitalManagementSystem.hr.enity.HumanResource;
import com.example.hospitalManagementSystem.hr.mapper.HumanResourceMapper;
import com.example.hospitalManagementSystem.hr.repo.HumanResourceRepo;
import com.example.hospitalManagementSystem.insurance.entity.Insurer;
import com.example.hospitalManagementSystem.insurance.mapper.InsurerMapper;
import com.example.hospitalManagementSystem.insurance.repo.InsurerRepo;
import com.example.hospitalManagementSystem.laboratory.repo.LaboratoristRepo;
import com.example.hospitalManagementSystem.laboratory.entity.Laboratorist;
import com.example.hospitalManagementSystem.laboratory.mapper.LaboratoristMapper;
import com.example.hospitalManagementSystem.pharmacy.entity.Pharmacist;
import com.example.hospitalManagementSystem.pharmacy.mapper.PharmacistMapper;
import com.example.hospitalManagementSystem.pharmacy.repo.PharmacistRepo;
import com.example.hospitalManagementSystem.receptionist.entity.Receptionist;
import com.example.hospitalManagementSystem.receptionist.mapper.ReceptionistMapper;
import com.example.hospitalManagementSystem.receptionist.repo.ReceptionistRepo;
import com.example.hospitalManagementSystem.registration.dto.RegistrationDto;
import com.example.hospitalManagementSystem.registration.mapper.RegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;



@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private static final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);

private final UserRepo userRepo;
private final RoleRepo roleRepo;
private final AddressRepo addressRepo;
private final RegistrationMapper registrationMapper;

private final DoctorMapper doctorMapper;
private  final DoctorRepo doctorRepo;

private final AccountantMapper accountantMapper;
private final AccountantRepo accountantRepo;

private  final LaboratoristMapper laboratoristMapper;
private final LaboratoristRepo  laboratoristRepo;

private final HeadNurseMapper headNurseMapper;
private final HeadNurseRepository  headNurseRepo;

private final HumanResourceMapper humanResourceMapper;
private final HumanResourceRepo humanResourceRepo;

private final ReceptionistMapper receptionistMapper;
private  final ReceptionistRepo receptionistRepo;

private final InsurerMapper insurerMapper;
private final InsurerRepo insurerRepo;

private final PharmacistMapper pharmacistMapper;
private final PharmacistRepo pharmacistRepo;


    @Transactional
    @Override
    public void registerUser(RegistrationDto dto) throws IOException {
        log.info("inside registerUser");

//        if (userRepo.existsByUsername(dto.getUsername())) {
//            log.info("inside username check");
//            throw new IllegalArgumentException("Username already exists");
//        }
//        if (userRepo.existsByEmail(dto.getEmail())) {
//            log.info("inside email check");
//            throw new IllegalArgumentException("Email already exists");
//        }


        log.info("getting role");
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        log.info("working on user");

        UserEntity user = registrationMapper.toUserEntity(dto, role);

        log.info("UserEntity userInfo hash: {}", System.identityHashCode(user.getUserInfo()));
        user = userRepo.save(user);
        System.out.println("printing user_id="+user.getUserId());
        //Normally, Spring Data JPA waits until the end of the transaction (like at the end of a @Transactional method) to write changes to the database.
        //
        //But in some cases, you need the database to assign values now, especially:
        //1. When you need the generated ID for further operations
//        You force Hibernate to insert the UserEntity now, get its generated userId, and then continue safely.
//         But it's not yet sent (committed) — you can still change or undo it(Because of trasactional)
        userRepo.flush(); //

        log.info("working on address");
        Address address = registrationMapper.toAddress(dto.getAddressDto());
        address = addressRepo.save(address);

        log.info("setting user info to user");

        try
        {
            UserInfo info = registrationMapper.toUserInfo(dto, user, address);
            log.info("registrationMapper.toUserInfo completed");
            info.setUserEntity(user);
            user.setUserInfo(info);
            log.info("user.setUserInfo(info) completed");

        } catch (Exception e) {
            throw new RuntimeException("Failed to set user info",e);
        }

        try {

         log.info("inside last try block");
            userRepo.save(user); // cascade saves UserInfo
            log.info("userRepo.save(user) completed");
        }
        catch (Exception e)
        {
            log.error("Error while saving user with UserInfo", e);
            throw new RuntimeException("Failed to save user", e);

        }

//        -------------------------------------------------------------------------------------


        //Role specific data persisting

        switch (role.getRoleName()) {
            case "ROLE_DOCTOR":
                log.info("Persisting data for role: {}", role.getRoleName());

                Doctor doctor = doctorMapper.toEntity(dto.getDoctorDto(), user);
                doctorRepo.save(doctor);
                break;

            case "ROLE_ACCOUNTANT":
                log.info("Persisting data for role: {}", role.getRoleName());
                Accountant accountant = accountantMapper.toEntity(dto.getAccountantDto(), user);
                accountantRepo.save(accountant);
                break;

            case "ROLE_LABORATORIST":
                log.info("Persisting data for role: {}", role.getRoleName());
                Laboratorist lab = laboratoristMapper.toEntity(dto.getLaboratoristDto(), user);
                laboratoristRepo.save(lab);
                break;

            case "ROLE_HEADNURSE":
                log.info("Persisting data for role: {}", role.getRoleName());
                HeadNurse headNurse = headNurseMapper.toEntity(dto.getHeadNurseDto(), user);
                headNurseRepo.save(headNurse);
                break;

            case "ROLE_HR":
                log.info("Persisting data for role: {}", role.getRoleName());
                HumanResource hr = humanResourceMapper.toEntity(dto.getHumanResourceDto(), user);
                humanResourceRepo.save(hr);
                break;

            case "ROLE_RECEPTIONIST":
                log.info("Persisting data for role: {}", role.getRoleName());
                Receptionist receptionist = receptionistMapper.toEntity(dto.getReceptionistDto(), user);
                receptionistRepo.save(receptionist);
                break;

            case "ROLE_INSURANCE":
                log.info("Persisting data for role: {}", role.getRoleName());
                Insurer insurer = insurerMapper.toEntity(dto.getInsurerDto(), user);
                insurerRepo.save(insurer);
                break;

            case "ROLE_PHARMACIST":
                log.info("Persisting data for role: {}", role.getRoleName());
                Pharmacist pharmacist = pharmacistMapper.toEntity(dto.getPharmacistDto(), user);
                pharmacistRepo.save(pharmacist);
                break;

            default:
                throw new IllegalArgumentException("Unsupported role: " + role.getRoleName());
        }

    }
}
