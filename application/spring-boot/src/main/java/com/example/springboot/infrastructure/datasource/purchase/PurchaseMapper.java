package com.example.springboot.infrastructure.datasource.purchase;

import com.example.domain.model.purchase.Purchase;
import com.example.domain.model.user.UserIdentifier;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseMapper {

  void insertTransaction(@Param("purchase") Purchase purchase);

  void insertAddress(@Param("purchase") Purchase purchase);

  void insertIdentity(@Param("purchase") Purchase purchase);

  List<Purchase> selectBy(@Param("userIdentifier") UserIdentifier userIdentifier);
}
