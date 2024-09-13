package com.hackerrank.tradingplatform.service;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.exception.EmailNotFoundException;
import com.hackerrank.tradingplatform.exception.UserAlreadyExitException;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {
    @Autowired
    private TraderRepository traderRepository;

    public void registerTrader(Trader trader) {
    	traderRepository.findByEmail(trader.getEmail())
    	.ifPresent(__ -> {
    		throw new UserAlreadyExitException();
    	});
        traderRepository.save(trader);
    }

    public Trader getTraderById(Long id) {
        return traderRepository.findById(id).get();
    }

    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email)
        		.orElseThrow(() -> new EmailNotFoundException());
    }

    public List<Trader> getAllTraders() {
        List<Trader> allByOrderByIdAsc = traderRepository.findAllByOrderByIdAsc();
		return allByOrderByIdAsc;
    }

    public void updateTrader(UpdateTraderDTO trader) {
        Trader existingTrader = traderRepository.findByEmail(trader.getEmail())
        		.orElseThrow(() -> new EmailNotFoundException());
        existingTrader.setName(trader.getName());
        traderRepository.save(existingTrader);
    }

    public void addMoney(AddMoneyTraderDTO trader) {
        Trader existingTrader = traderRepository.findByEmail(trader.getEmail())
        		.orElseThrow(() -> new EmailNotFoundException());
        existingTrader.setBalance(existingTrader.getBalance() + trader.getAmount());
        traderRepository.save(existingTrader);
    }
}
