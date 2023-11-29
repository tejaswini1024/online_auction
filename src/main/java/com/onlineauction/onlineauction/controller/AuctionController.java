package com.onlineauction.onlineauction.controller;

import com.onlineauction.onlineauction.exception.ResourceNotFoundException;
import com.onlineauction.onlineauction.model.Auction;
import com.onlineauction.onlineauction.response.AuctionResponse;
import com.onlineauction.onlineauction.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @PostMapping
    public ResponseEntity<Object> createAuction(@RequestBody @Valid Auction auction) {
        logger.info("Request received: Creating Auction");
        Auction createdAuction = auctionService.createAuction(auction);
        logger.info("Auction Created Successfully");
        return AuctionResponse.createdAuctionBuilder(HttpStatus.CREATED,createdAuction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuctionById(@PathVariable Long id) {
        logger.info("Request received: Getting Auction by Id");
        Optional<Auction> auction = auctionService.getAuctionById(id);
        if (auction.isPresent()) {
            logger.info("Auction information received successfully");
            return AuctionResponse.getAuctionBuilder(HttpStatus.OK, auction);
        } else  {
            logger.error("Auction not found with ID: {}", id);
            return AuctionResponse.getAuctionBuilder(HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAuctions() {
        logger.info("Request received: Getting all Auctions");
        List<Auction> auctions = auctionService.getAllAuctions();
        logger.info("All Auctions information received successfully");
        return AuctionResponse.getAllAuctionsBuilder(HttpStatus.OK,auctions);
    }


}
