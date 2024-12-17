package com.cgr.base.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.cgr.base.application.auth.mapper.UserMapper;
import com.cgr.base.domain.models.UserModel;
import com.cgr.base.domain.repository.IUserRepository;
import com.cgr.base.infrastructure.persistence.entity.UserEntity;
import com.cgr.base.infrastructure.persistence.repository.auth.IAuthRepositoryJpa;

@Component
public class AuthRepositoryAdapterImpl implements IUserRepository {

  private final IAuthRepositoryJpa authRepositoryJpa;

  public AuthRepositoryAdapterImpl(IAuthRepositoryJpa authRepositoryJpa) {
    this.authRepositoryJpa = authRepositoryJpa;
  }

  @Override
  public UserModel findBySAMAccountName(String sAMAccountName) {

    try {
      UserEntity userEntity = authRepositoryJpa.findBysAMAccountName(sAMAccountName);
      if (userEntity.hashCode() > 0) {
        return UserMapper.INSTANCE.toUserEntity(userEntity);
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findBySAMAccountName'");
  }

}
