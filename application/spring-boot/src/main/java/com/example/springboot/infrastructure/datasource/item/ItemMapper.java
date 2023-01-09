package com.example.springboot.infrastructure.datasource.item;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.ItemName;
import com.example.query.ItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {

  Item selectById(@Param("itemIdentifier") ItemIdentifier itemIdentifier);

  Item selectByName(@Param("itemName") ItemName itemName);

  List<Item> selectBy(@Param("itemCriteria") ItemCriteria itemCriteria);

  int selectCount(@Param("itemCriteria") ItemCriteria itemCriteria);
}
