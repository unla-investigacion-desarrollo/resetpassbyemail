package com.ppp.resetpasswordbyemail.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

/*
import com.unla.agroecologiaiot.shared.paginated.PagerParametersModel;
 */

import com.ppp.resetpasswordbyemail.models.ApplicationUserModel;

public interface IApplicationUserService {

    public ResponseEntity<String> saveOrUpdate(ApplicationUserModel model);

    public ResponseEntity<String> put(ApplicationUserModel model, long id);

    public ResponseEntity<String> delete(long id);

    public ResponseEntity<String> getById(long id);

    public ResponseEntity<String> getByUsername(String username);

    public ResponseEntity<String> getListByRoleId(long id);

 //   public ResponseEntity<String> getList(PagerParametersModel pageParameters, Optional<Long> userId);

    public ResponseEntity<String> logout(String token);

}
