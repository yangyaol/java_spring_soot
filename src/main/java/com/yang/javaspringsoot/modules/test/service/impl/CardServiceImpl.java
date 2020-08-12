package com.yang.javaspringsoot.modules.test.service.impl;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.test.entity.Card;
import com.yang.javaspringsoot.modules.test.repository.CardRepository;
import com.yang.javaspringsoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:
 * @create: 2020-08-12 20:05
 **/
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status,"insert succer",card);
    }
}
