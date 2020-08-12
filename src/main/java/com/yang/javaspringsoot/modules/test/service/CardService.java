package com.yang.javaspringsoot.modules.test.service;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.test.entity.Card;



public interface CardService {

    Result<Card> insertCard(Card card);
}
