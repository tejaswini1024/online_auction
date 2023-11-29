package com.onlineauction.onlineauction.service;

import com.onlineauction.onlineauction.exception.ResourceNotFoundException;
import com.onlineauction.onlineauction.model.Auction;
import com.onlineauction.onlineauction.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    public Auction createAuction(@Valid Auction auction) {
        // validation logic
        // business logic
        return auctionRepository.save(auction);
    }
    public Optional<Auction> getAuctionById(Long id) {
        Optional<Auction> checkAuction= auctionRepository.findById(id);
        if(checkAuction.isEmpty())
        {
                throw new ResourceNotFoundException("Auction not found with id: " + id);
        }
        else{
            return checkAuction;
        }
    }
    public List<Auction> getAllAuctions() {
        return (List<Auction>) auctionRepository.findAll();
    }

    // other methods

}